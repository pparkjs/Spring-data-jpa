package study.datajpa.repository.query;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 해당 repository는 화면단에 맞춤 엄청 복잡한 쿼리를 놓는 Repository임
 *  항상 사용자 정의 리포지토리가 필요한것은 아니기에 이렇게 복잡한 쿼리가 있는 경우
 *  이런식으로 별도로 분리해서 쓰는 거 추천
 *  ex. 복잡한 nativeQuery나 복잡한 queryDsl
 */
@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final EntityManager em;
}
