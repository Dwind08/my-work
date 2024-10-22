package com.dwind.mywork.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 新增EIP流程(附件)
 */
@Data
@NoArgsConstructor
public class Attachment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5529679878340882130L;

	/**
	 * 附件的关键字，富文本模式下为"fdAttachment"，表单模式下为附件控件的id
	 */
	private String fdKey;

	/**
	 * 附件文件名
	 */
	private String fdFileName;

	/**
	 * 附件内容，格式为字节编码
	 */
	private String fdAttachment;
}
