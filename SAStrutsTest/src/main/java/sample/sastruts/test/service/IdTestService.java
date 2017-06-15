package sample.sastruts.test.service;

import java.util.List;
import javax.annotation.Generated;
import sample.sastruts.test.entity.IdTest;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static sample.sastruts.test.names.IdTestNames.*;

/**
 * {@link IdTest}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.40", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2017/06/15 22:18:22")
public class IdTestService extends AbstractService<IdTest> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public IdTest findById(String id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<IdTest> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}