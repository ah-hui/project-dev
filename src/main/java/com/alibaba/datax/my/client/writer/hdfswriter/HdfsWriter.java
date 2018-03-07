package com.alibaba.datax.my.client.writer.hdfswriter;

import com.alibaba.datax.my.client.job.content.Parameter;
import lombok.Getter;
import lombok.Setter;

/**
 * HdfsWriter插件 - 写入数据到Hdfs/Hive主库
 * 参考基本配置模板-HdfsWriter.json
 * 底层实现：HdfsWriter提供向HDFS文件系统指定路径中写入TEXTFile文件和ORCFile文件,文件内容可与hive中表关联。
 * 功能与限制：https://github.com/alibaba/DataX/blob/master/hdfswriter/doc/hdfswriter.md
 *
 * @author lgh
 */
@Getter
@Setter
public class HdfsWriter extends Parameter {


}
