## 실전! 스프링 부트와 JPA 활용 1 - 웹 애플리케이션 개발

#### 젤 중요한 거!
* 모든 연관관계는 지연로딩으로 설정!
  * 즉시로딩(EAGER)은 예측이 어렵고 어떤 SQL이 실행될지 추적하기 어렵다. 특히 JPQL을 실행할 때 N+1 문제 발생
  * 실무에서는 모든 연관관계는 지연로딩(LAZY)로 설정
  * 연관된 엔티티를 함께 DB에서 조회해야 하면 fetch join 또는 엔티티 그래프 기능을 사용
  * OneToOne, ManyToOne 관계는 기본이 즉시 로딩이므로 직접 지연로딩으로 설계해야 한다. 
* 컬렉션은 필드에서 초기화 하자.
  * 컬렉션은 필드에서 바로 초기화 하는 것이 null 문제에서 안전하다. < 잘 모르겠음