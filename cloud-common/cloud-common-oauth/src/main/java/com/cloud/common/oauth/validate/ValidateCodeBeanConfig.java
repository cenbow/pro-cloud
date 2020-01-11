package com.cloud.common.oauth.validate;


import com.cloud.common.oauth.properties.SecurityProperties;
import com.cloud.common.oauth.validate.image.ImageCodeGenerator;
import com.cloud.common.oauth.validate.sms.DefaultSmsCodeSender;
import com.cloud.common.oauth.validate.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  验证码相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 *  模块默认的配置。
 * @author Aijm
 * @since 2019/5/26
 */
@Configuration
public class ValidateCodeBeanConfig {


	/**
	 * 图片验证码图片生成器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator(SecurityProperties securityProperties) {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}

	/**
	 * 短信验证码发送器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

}
