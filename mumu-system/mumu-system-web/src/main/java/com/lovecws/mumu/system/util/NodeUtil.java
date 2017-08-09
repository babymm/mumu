package com.lovecws.mumu.system.util;

import com.lovecws.mumu.common.core.utils.StringUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class NodeUtil {

    public static List iterator(List<NodeBean> nodes, String prefix, String fieldName) {
        if (nodes == null) {
            return null;
        }
        List ts = new ArrayList();
        //获取顶级节点
        List<NodeBean> topNodes = getTopNodes(nodes);

        for (NodeBean topNode : topNodes) {
            ts.add(topNode.getData());
            iteratorNodes(topNode.getId(),nodes,ts,prefix, fieldName,1);
        }
        return ts;
    }

    private static void iteratorNodes(String id, List<NodeBean> nodes, List ts,String prefix,String fieldName,int deepth) {
        try {
            for (NodeBean node : nodes) {
                if (node.getPid().equals(id)) {
                    int temp = deepth;
                    Object nodeData = node.getData();
                    //如果附带前缀 则通过反射来重置字段值
                    if (!StringUtil.isEmpty(prefix)&&!StringUtil.isEmpty(fieldName)) {
                        Field field = nodeData.getClass().getDeclaredField(fieldName);
                        if(field!=null){
                            field.setAccessible(true);
                            Object value = field.get(nodeData);
                            field.set(nodeData, getCurrentPrefix(prefix,deepth)+value);
                        }
                    }
                    ts.add(nodeData);
                    iteratorNodes(node.getId(), nodes, ts, prefix, fieldName, ++temp);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentPrefix(String prefix, int deepth) {
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<deepth;i++){
            builder.append(prefix);
        }
        return builder.toString();
    }

    private static List<NodeBean> getTopNodes(List<NodeBean> nodes) {
        List<NodeBean> topNodes = new ArrayList<NodeBean>();
        for (NodeBean node : nodes) {
            String pid = node.getPid();
            if (pid==null||"0".equals(pid)||"".equals(pid)) {
                topNodes.add(node);
            }
        }
        return topNodes;
    }

    public static class NodeBean {
        private String id;
        private String pid;
        private Object data;

        public NodeBean(String id, String pid, Object data) {
            this.id = id;
            this.pid = pid;
            this.data = data;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
