package com.thread.immuable;

/**
 * 运维中心对接的类
 * 模式角色
 */
public class OMCAgent extends Thread{

    @Override
    public void run() {
        boolean updatedTableModificationMsg = false;
        String updateTableName = null;
        while (true){
            /**
             * 解析完数据，重置instance实例
             */
            if(updatedTableModificationMsg){
                if("MMSCInfo".equals(updateTableName)){
                    MMSRouter.setInstance(new MMSRouter());
                }
            }
        }
    }
}
