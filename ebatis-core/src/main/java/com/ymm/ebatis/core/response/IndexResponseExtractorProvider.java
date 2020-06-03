package com.ymm.ebatis.core.response;

import com.google.auto.service.AutoService;
import com.ymm.ebatis.core.meta.RequestType;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.core.ResolvableType;

/**
 * @author 章多亮
 * @since 2020/1/18 17:02
 */
@AutoService(ResponseExtractorProvider.class)
public class IndexResponseExtractorProvider extends AbstractResponseExtractorProvider {
    public IndexResponseExtractorProvider() {
        super(RequestType.INDEX);
    }

    @Override
    protected ResponseExtractor<?> getResponseExtractor(ResolvableType resolvedResultType) {
        Class<?> resultClass = resolvedResultType.resolve();

        if (Boolean.class == resultClass || boolean.class == resultClass) {
            return BooleanIndexResponseExtractor.INSTANCE;
        } else if (IndexResponse.class == resultClass) {
            return RawResponseExtractor.INSTANCE;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}