package io.manbang.ebatis.core.builder;

import io.manbang.ebatis.core.annotation.Bool;
import io.manbang.ebatis.core.meta.ClassMeta;
import io.manbang.ebatis.core.meta.ConditionMeta;
import io.manbang.ebatis.core.meta.FieldMeta;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;


/**
 * @author duoliang.zhang
 */
@Slf4j
class BoolQueryBuilderFactory extends AbstractQueryBuilderFactory<BoolQueryBuilder, Bool> {
    static final BoolQueryBuilderFactory INSTANCE = new BoolQueryBuilderFactory();

    private BoolQueryBuilderFactory() {
    }

    @Override
    protected BoolQueryBuilder doCreate(ConditionMeta meta, Object condition) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        Map<Class<? extends Annotation>, List<FieldMeta>> queryClauses = meta == null ?
                ClassMeta.of(condition.getClass()).getQueryClauses() : meta.getQueryClauses(condition);

        queryClauses.forEach((key, fieldMetas) -> QueryClauseType.valueOf(key).addQueryBuilder(builder, fieldMetas, condition));

        return builder;
    }
}

