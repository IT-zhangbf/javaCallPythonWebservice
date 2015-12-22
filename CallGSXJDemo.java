import org.codehaus.xfire.client.Client;

import java.net.URL;

/**
 * Created by devbwh on 15-12-22.
 * 调用工商巡检webservice  demo
 */


public class CallGSXJDemo {
    //懒汉式单例类.在第一次调用的时候实例化自己
    private static String url = "http://10.10.185.71:7790/SOAP?wsdl";

    //返回xfire调用客户端对象
    private static Client getClient() throws  Exception{
        Client client = new Client(new URL(url));
        return client;
    }


    /**
     * 修改用户密码
     * @param userName   用户名
     * @param password   原始密码
     * @param newPassword  新密码
     * @return  xml,返回格式如：<?xml version='1.0' encoding='UTF-8'?><root><State>1</State></root>
     * @throws Exception
     */
    public static String callUpdatePassword(String userName,String password,String newPassword) throws Exception{
        Client client = getClient();
        Object[] results = client
                .invoke("updatePassword", new Object[] { userName,password,newPassword });
        return results[0].toString();

    }


    /**
     * 添加通知邮箱
     * @param userName
     * @param password
     * @param mailAddress
     * @return  xml,返回格式如：<?xml version='1.0' encoding='UTF-8'?><root><State>4</State></root>
     * @throws Exception
     */
    public static String callAddReceviedMail(String userName,String password,String mailAddress) throws Exception{
        Client client = getClient();
        Object[] results = client
                .invoke("addReceivedMail", new Object[] { userName,password,mailAddress });
        return results[0].toString();
    }


    /**
     * 取消任务
     * @param userName  用户名
     * @param password  密码
     * @param taskId    任务编号
     * @return  xml,格式如：<?xml version='1.0' encoding='UTF-8'?><root><State>6</State></root>
     * @throws Exception
     */
    public static String callCancelTask(String userName,String password,String taskId) throws Exception{
        Client client = getClient();
        Object[] results = client
                .invoke("cancelTask", new Object[] { userName,password,taskId });
        return results[0].toString();
    }


    /**
     * 获取任务清单
     * @param userName  用户名
     * @param password  密码
     * @param taskId    任务编号
     * @return  xml,格式如：<?xml version='1.0' encoding='UTF-8'?>
    <root><taskId>85adbab6-972d-11e5-987a-b8aeed70b4f0</taskId><tasks><taskResult><taskResultId>1</taskResultId><successCount>0</successCount><waitCount>0</waitCount></taskResult></tasks></root>
     * @throws Exception
     */
    public static String callGetTaskList(String userName,String password,String taskId) throws Exception{
        Client client = getClient();
        Object[] results = client
                .invoke("getTaskList", new Object[] { userName,password,taskId });
        return results[0].toString();
    }


    /**
     * 获取亮照结果
     * @param userName  用户名
     * @param password  密码
     * @param taskId    任务编号
     * @return  xml,格式如：<?xml version='1.0' encoding='UTF-8'?>
     *<root><taskId>85adbab6-972d-11e5-987a-b8aeed70b4f0</taskId><tasks><taskResult><taskResultId>1</taskResultId><task><cname>xxx</cname><url>xxx</url><state>1</state></task></taskResult></tasks></root>
     * @throws Exception
     */
    public static String callGetALResult(String userName,String password,String taskId) throws Exception{
        Client client = getClient();
        Object[] results = client
                .invoke("getALResult", new Object[] { userName,password,taskId });
        return results[0].toString();
    }


    /**
     * 发送需要抓取的公司信息
     * @param userName  用户名
     * @param password  密码
     * @param companyFile   公司信息xml格式的字符串
     * @param intevalDay    间隔时间：可以为空，表示任务完成之后进行下一次抓取任务的间隔时间，单位为(天)
     * @param packId        发送的文件包名，将一个大文件分解为若干次进行发送的小包名
     * @param hash          每一次发送的companyFile接受完整之后的hash值
     * @param taskCount     总共需要接受的任务总数
     * @return  xml，格式如：<?xml version='1.0' encoding='UTF-8'?><root><State>1</State></root>
     * @throws Exception
     */
    public static String callCheckAL(String userName,String password,String companyFile,String intevalDay,String packId,String hash,String taskCount) throws Exception{
        Client client = getClient();
        Object[] results = client
                .invoke("checkAL", new Object[] { userName,password,companyFile,intevalDay,packId,hash,taskCount });
        return results[0].toString();
    }







    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args){
        try{
            //测试修改用户密码
            //String r = callUpdatePassword("admin","admin","test");
            //测试添加任务通知邮箱
            //String r = callAddReceviedMail("admin","admin","877798942@qq.com");
            //测试取消任务
            //String r = callCancelTask("admin","admin","c202c376-972d-11e5-987a-b8aeed70b4f0");
            //测试获取任务清单
            //String r = callGetTaskList("admin","test","85adbab6-972d-11e5-987a-b8aeed70b4f0");
            //获取任务结果
            //String r = callGetALResult("admin","test","85adbab6-972d-11e5-987a-b8aeed70b4f0");
            String companyFile = "<?xml version=\"1.0\" encoding=\"utf-8\"?><companies>\n" +
                    "    <root>\n" +
                    "        <com>\n" +
                    "            <cname>xxx</cname>\n" +
                    "            <url>xxx</url>\n" +
                    "        </com>\n" +
                    "        <com>\n" +
                    "            <cname>xxx</cname>\n" +
                    "            <url>xxx</url>\n" +
                    "        </com>\n" +
                    "    </root>\n" +
                    "</companies>";
            String r = callCheckAL("admin","test",companyFile,"","1","sadfasdf","10000");
            System.out.println("r:"+r);
        }catch (Exception e){
           e.printStackTrace();
        }

    }

}





