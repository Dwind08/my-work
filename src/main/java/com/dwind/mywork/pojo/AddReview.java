package com.dwind.mywork.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 新增EIP流程
 */
@Data
@NoArgsConstructor
public class AddReview implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -8362249866179336097L;

	/**
	 * 文档标题
	 */
	private String docSubject;

	/**
	 * 文档模板ID
	 */
	private String fdTemplateId;

	/**
	 * 文档的富文本内容
	 */
	private String docContent;

	/**
	 * 流程表单数据<br>
	 * 样例：{"fd_2eddbf023c8292":"张三", "fd_2edd2f83f68242":"咨询部"}
	 */
	private String formValues;

	/**
	 * 文档状态，可以为草稿（"10"）或者待审（"20"）两种状态，默认为待审
	 */
	private String docStatus;

	/**
	 * 流程发起人<br>
	 * 样例： {"LoginName": "houjw"}
	 */
	private String docCreator;

	/**
	 * 文档关键字，格式为["关键字1","关键字2"...]
	 */
	private String fdKeyword;

	/**
	 * 辅类别，格式为["辅类别1的ID","辅类别2的ID"...]
	 */
	private String docProperty;

	/**
	 * 流程参数<br>
	 * 
	 * 样例：{auditNode:"请审核", futureNodeId:"N7", changeNodeHandlers:["N7:1183b0b84ee4f581bba001c47a78b2d9;131d019fbac792eab0f0a684c8a8d0ec"]}
	 */
	private String flowParam;

	/**
	 * 附件列表
	 */
	private List<Attachment> attachmentForms;
}
