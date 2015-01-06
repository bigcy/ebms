package com.ebupt.ebms.hessian;

/**
 * @author QiChen Create on 2011-3-16
 * @version 1.0
 */
public interface EBMSHessian {

	/**
	 * 设置taskflag的值
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public void setTaskFlag(String terminalId, String flag);

	/**
	 * 获取taskflag的值
	 * 
	 * @param terminalId
	 * @return
	 */
	public String getTaskFlag(String terminalId);

	/**
	 * 根据键删除对应的值
	 * 
	 * @param key
	 * @return
	 */
	public boolean deleteValueByKey(String key);

	/**
	 * 返回终端最后一次连接的时间戳
	 * 
	 * @param terminalId
	 */
	public String getTermSelectTime(String terminalId);

	/**
	 * 根据群组ID设置任务
	 * 
	 * @param groupids
	 *            多个用逗号分隔。
	 * @param taskType
	 *            01 ~ 12 播放内容任务 01 传播放任务id，一次只能一个 软件升级任务 02 控制类 03 即时消息任务 04
	 *            配置类任务 05 天气预报任务 06 播放任务控制
	 *            07：暂停、恢复、撤销。需要先将playlistgroup的status改成相应状态再调该接口
	 *            ,taskIds=serialno 终端信息上报 08 终端工作状态上报 09 在播内容上报任务 10 实时数据任务 11
	 *            上报日志任务 12
	 * @param taskIds
	 *            :任务id，对应每个类型任务的id 如果taskType = 03 ： 那么taskids为（0:重启 1:休眠 2:唤醒
	 *            3:磁盘清空），多个用逗号分隔。 taskIds数据由前台调用时传入
	 *            operatorid:操作员id，为了避免根节点群组id重复的情况，增加操作员id来筛选终端
	 * @return
	 */
	public String setTasksByGroupids(String groupids, String taskType, String taskIds, String operatorid);

	/**
	 * 根据终端ID设置任务
	 * 
	 * @param terminalId
	 * @param taskType
	 *            01 ~ 12
	 * @param taskIds
	 *            如果taskType = 03，那么taskids必须有值，并且为（0、1、2、3、4、5）
	 * @param resourceXMLPath
	 *            前台调用时可以传null
	 * @param status
	 *            前台调用时可以传null taskIds数据由前台调用时传入 多个用逗号分隔。
	 * @return
	 */
	public String setTasksByTerminalIds(String terminalId, String taskType, String taskIds, String resourceXMLPath,
			String status);

	/**
	 * 验证任务是否冲突。（精确到群组）
	 * 
	 * @param groupIds
	 *            (多个用逗号分隔),playlistId(播放列表ID) startDate|endDate : 开始日期|结束日期
	 * @return String flag|reason(true or false | 如果为false 返回原因。以竖线分隔)
	 */
	public String validation(String groupIds, String playlistid, String startDate, String endDate, String priority);

	/**
	 * 
	 * @param name
	 *            :视频文件名称 size:图片大小 200x100
	 * @return 视频播放时长
	 */
	public String getVideoThumb(String name, String size);

	/**
	 * @param path
	 *            :升级包所在路径
	 * @return
	 */
	public String softupdate(String factoryid, String version, String path);

	/**
	 * @param srcPath原图片路径
	 * @param midPath要叠加的图片路径
	 * @param destPath目标图片路径
	 * @param position叠加位置坐标
	 * @return destPath
	 */
	public String composite(String srcPath, String midPath, String destPath, String position);

	/**
	 * 连续截屏
	 * 
	 * @param terminalId
	 * @param flag
	 *            0：停止截屏 1：开始截屏
	 * @return
	 */
	public String continueGetSnapshot(String terminalId, String flag);

	/**
	 * 邮件发送
	 * @param to 接收者邮箱地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @return
	 */
	public String sendMail(String to, String subject, String content);
}