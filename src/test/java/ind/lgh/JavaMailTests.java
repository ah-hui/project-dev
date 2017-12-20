package ind.lgh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaMailTests {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine thymeleafEngine;

    /**
     * 发送简单邮件
     */
    @Test
    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("673350601@qq.com");
        message.setTo("13085399920@163.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试发送邮件");
        mailSender.send(message);
    }

    /**
     * 发送附件邮件
     *
     * @throws MessagingException
     * @throws FileNotFoundException
     */
    @Test
    public void sendAttachmentsMail() throws MessagingException, FileNotFoundException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("673350601@qq.com");
        helper.setTo("13085399920@163.com");
        helper.setSubject("主题：有附件的邮件");
        helper.setText("测试-请查收附件");
        // 附件
        File file = ResourceUtils.getFile("classpath:static/images/weixin.jpg");
        FileSystemResource resource = new FileSystemResource(file);
        helper.addAttachment("附件-1.jpg", resource);
        helper.addAttachment("附件-2.jpg", resource);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送嵌入静态资源的邮件
     *
     * @throws MessagingException
     * @throws FileNotFoundException
     */
    @Test
    public void sendInlineMail() throws MessagingException, FileNotFoundException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("673350601@qq.com");
        helper.setTo("13085399920@163.com");
        helper.setSubject("主题：嵌入静态资源的邮件");
        // cid在下文定义并对齐
        helper.setText("<html><body><img src=\"cid:weixin\"></body></html>", true);
        // 静态内容定义
        File file = ResourceUtils.getFile("classpath:static/images/weixin.jpg");
        FileSystemResource resource = new FileSystemResource(file);
        helper.addInline("weixin", resource);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送Thymeleaf模板邮件
     *
     * @throws MessagingException
     */
    @Test
    public void sendTemplateMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("673350601@qq.com");
        helper.setTo("13085399920@163.com");
        helper.setSubject("主题：模板邮件");
        // 邮件模板
        // Map<String,Object> model = new HashMap<>();
        // model.put("name","lgh");
        Context context = new Context();
        context.setVariable("name", "lgh");
        // 解析的默认路径为/resources/templates
        String content = thymeleafEngine.process("email/defaultEmailTemplate", context);
        helper.setText(content, true);
        mailSender.send(mimeMessage);
    }
}
