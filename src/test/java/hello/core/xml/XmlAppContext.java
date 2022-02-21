package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * 스프링 컨테이너는 다양한 형식의 설정 정보를 받아드릴 수 있도록 유연하게 설계되어있음 (e.g. 자바 코드, XML, Groovy)
 * 자바 코드로 된 설정 정보를 사용할 경우: AnnotationConfigApplicationContext 클래스를 사용
 * XML 설정 정보를 사용할 경우: GenericXmlApplicationContext 사용
 *      -> 최근에는 xml 기반의 설정은 잘 사용하지 않음. xml 사용시 컴파일 없이 설정 정보 변경 가능.
 */

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
