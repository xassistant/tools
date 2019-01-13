package syslog.flume;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.oio.OioDatagramChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SyslogUDPSource {

    private int port;
    private int maxsize = 1 << 16; // 64k is max allowable in RFC 5426
    private String host = null;
    private Channel nettyChannel;
    private Map<String, String> formaterProp;
    private Set<String> keepFields;
    private String clientIPHeader;
    private String clientHostnameHeader;

    private static final Logger logger = LoggerFactory.getLogger(SyslogUDPSource.class);

    // Default Min size
    public static final int DEFAULT_MIN_SIZE = 2048;
    public static final int DEFAULT_INITIAL_SIZE = DEFAULT_MIN_SIZE;

    public class syslogHandler extends SimpleChannelHandler {
        private SyslogUtils syslogUtils = new SyslogUtils(DEFAULT_INITIAL_SIZE, null, true);
        private String clientIPHeader;
        private String clientHostnameHeader;

        public void setFormater(Map<String, String> prop) {
            syslogUtils.addFormats(prop);
        }

        public void setKeepFields(Set<String> keepFields) {
            syslogUtils.setKeepFields(keepFields);
        }

        public void setClientIPHeader(String clientIPHeader) {
            this.clientIPHeader = clientIPHeader;
        }

        public void setClientHostnameHeader(String clientHostnameHeader) {
            this.clientHostnameHeader = clientHostnameHeader;
        }

        int i = 0;

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent mEvent) {
            try {

                syslogUtils.setEventSize(maxsize);
                ChannelBuffer buffer = (ChannelBuffer) mEvent.getMessage();
//                byte[] recByte = buffer.copy().toByteBuffer().array();
                byte[] recByte = buffer.copy().toByteBuffer().array();

                String msg = new String(recByte);
                System.out.println(msg);

//                Event e = syslogUtils.extractEvent((ChannelBuffer) mEvent.getMessage());
//                if (e == null) {
//                    return;
//                }
//
//                if (clientIPHeader != null) {
//                    e.getHeaders().put(clientIPHeader, SyslogUtils.getIP(mEvent.getRemoteAddress()));
//                }
//
//                if (clientHostnameHeader != null) {
//                    e.getHeaders().put(clientHostnameHeader, SyslogUtils.getHostname(mEvent.getRemoteAddress()));
//                }
//                byte[] body = e.getBody();
//
//                System.out.println(new String(body));
            } catch (ChannelException ex) {
                logger.error("Error writting to channel", ex);
                return;
            } catch (RuntimeException ex) {
                logger.error("Error parsing event from syslog stream, event dropped", ex);
                return;
            }
        }
    }

    public void start() {
        // setup Netty server
        ConnectionlessBootstrap serverBootstrap = new ConnectionlessBootstrap(new OioDatagramChannelFactory(Executors.newCachedThreadPool()));
        final syslogHandler handler = new syslogHandler();
        handler.setFormater(formaterProp);
        handler.setKeepFields(keepFields);
        handler.setClientIPHeader(clientIPHeader);
        handler.setClientHostnameHeader(clientHostnameHeader);
        serverBootstrap.setOption("receiveBufferSizePredictorFactory", new AdaptiveReceiveBufferSizePredictorFactory(DEFAULT_MIN_SIZE, DEFAULT_INITIAL_SIZE, maxsize));
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() {
                return Channels.pipeline(handler);
            }
        });

        if (host == null) {
            nettyChannel = serverBootstrap.bind(new InetSocketAddress(port));
        } else {
            nettyChannel = serverBootstrap.bind(new InetSocketAddress(host, port));
        }

    }

    public void stop() {
        logger.info("Syslog UDP Source stopping...");
        if (nettyChannel != null) {
            nettyChannel.close();
            try {
                nettyChannel.getCloseFuture().await(60, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                logger.warn("netty server stop interrupted", e);
            } finally {
                nettyChannel = null;
            }
        }

    }

    public void configure(Context context) {
        Configurables.ensureRequiredNonNull(context, SyslogSourceConfigurationConstants.CONFIG_PORT);
        port = context.getInteger(SyslogSourceConfigurationConstants.CONFIG_PORT);
        host = context.getString(SyslogSourceConfigurationConstants.CONFIG_HOST);
        formaterProp = context.getSubProperties(SyslogSourceConfigurationConstants.CONFIG_FORMAT_PREFIX);
        keepFields = SyslogUtils.chooseFieldsToKeep(context.getString(SyslogSourceConfigurationConstants.CONFIG_KEEP_FIELDS, SyslogSourceConfigurationConstants.DEFAULT_KEEP_FIELDS));
        clientIPHeader = context.getString(SyslogSourceConfigurationConstants.CONFIG_CLIENT_IP_HEADER);
        clientHostnameHeader = context.getString(SyslogSourceConfigurationConstants.CONFIG_CLIENT_HOSTNAME_HEADER);

    }

    public static void main(String[] args) {
        SyslogUDPSource syslogUDPSource = new SyslogUDPSource();
        Context context = new Context();
        context.put("port", "514");
        syslogUDPSource.configure(context);
        syslogUDPSource.start();
    }
}
