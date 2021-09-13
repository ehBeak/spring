package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepositroy {

    // select m from Member m where m.name = ? 이런 방식으로 규칙이 있음.
    // findBy'name'
    @Override
    Optional<Member> findByName(String name);
}
