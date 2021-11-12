package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

/* 생성자를 주입하자

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 추상에만 의존해야하는데 구현 클래스에도 의존하고있음. -> DIP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 그래서 DiscountPolicy 변경시 OrderServiceImpl를 수정해야함. -> OCP 위반
//  => 인터페이스에만 의존하도록 의존관계를 변경해야함.
    private DiscountPolicy discountPolicy; // -> 인터페이스에만 의존함. 그러나 널포인터 익셉션이 발생한다.
//  => 어떻게 해결해? 누군가 클라이언트인 OrderServiceImpl에 DiscountPolicy 객체를 대신 생성해서 주입해줘야 한다. == 의존성 주입

 */

    // 모두 인터페이스에만 의존함 -> DIP 준수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
