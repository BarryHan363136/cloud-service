package com.barry.cloud.platform.es.service;

import com.barry.cloud.platform.es.base.BaseESTest;
import com.barry.cloud.platform.es.dao.ArticleRepository;
import com.barry.cloud.platform.es.entity.Article;
import com.barry.cloud.platform.es.id.IdWorker;
import com.barry.cloud.platform.es.utils.JSONMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

@Slf4j
public class ArticleServiceTest extends BaseESTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private IdWorker idWorker = null;

    @Before
    public void init(){
        idWorker = new IdWorker(2L);
    }

    @Test
    public void testSave(){
        Article article = new Article();
        article.setId(idWorker.nextId().toString());
        article.setUrl("https://blog.csdn.net/weixin_33753845/article/details/86018112");
        article.setContent("记一次ElasticSearch重启之后shard未分配问题的解决");
        article.setAuthor("楚名");
        article.setCreateDate(new Date());
        article.setRemark("发射点立刻反击埃里克的时间认为若iu哦iu发的是你发士大夫人家嗯我去了客人交期未来科技哈十分罕见");
        article.setReview("五星好评");
        article.setSerNo(1000);
        article = articleRepository.save(article);
        if (article!=null){
            log.info("========================>"+JSONMapper.writeObjectAsString(article));
        }
    }

    @Test
    public void testModify(){
        Article article = new Article();
        article.setId(idWorker.nextId().toString());
        article.setUrl("2");
        article.setContent("22");
        article.setAuthor("楚名2");
        article.setCreateDate(new Date());
        article.setRemark("222");
        article.setReview("2222");
        article.setSerNo(1001);
        articleRepository.save(article);
        if (article!=null){
            log.info("========================>"+JSONMapper.writeObjectAsString(article));
        }
    }

    /**
     * 精确匹配
     * Sort sort = new Sort(Sort.Direction.ASC,"id");
     */
    @Test
    public void testExactQuery() {
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must(QueryBuilders.termQuery("author", "楚名"));
        Page<Article> page = articleRepository.search(qb, PageRequest.of(0, 50));
        if (page != null) {
            log.info("===testExactQuery==============>" + JSONMapper.writeObjectAsString(page));
        }
    }
    @Test
    public void testExactQuery2() {
        //检索条件
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        bqb.must(QueryBuilders.termQuery("author", "楚名"));

        //构建查询
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(bqb)
                .withPageable(PageRequest.of(0, 1, new Sort(Sort.Direction.DESC,"serNo")))
                .withTypes("article")
                .build();
        Page<Article> page = articleRepository.search(query);
        if (page != null) {
            log.info("===testExactQuery==============>" + JSONMapper.writeObjectAsString(page));
        }
    }
    @Test
    public void testExactQuery3() {
        //Iterable<Article> iterable = articleRepository.search(QueryBuilders.matchPhraseQuery("author", "楚名"));
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("author", "楚名");
        Iterable<Article> iterable = articleRepository.search(termQueryBuilder);
        if (iterable != null) {
            log.info("===testExactQuery==============>" + JSONMapper.writeObjectAsString(iterable));
        }
    }

    @Test
    public void query() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(matchPhraseQuery("author", "楚名")).
                withPageable(PageRequest.of(0, 50)).
                build();

        List<Article> list = elasticsearchTemplate.queryForList(searchQuery, Article.class);
        if (list != null) {
            log.info("===testExactQuery==============>" + JSONMapper.writeObjectAsString(list));
        }
    }
    @Test
    public void query2() {
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("author").is("楚名"))).setPageable(
                PageRequest.of(0, 10));
        Page<Article> page = elasticsearchTemplate.queryForPage(criteriaQuery, Article.class);
        if (page != null) {
            log.info("===testExactQuery==============>" + JSONMapper.writeObjectAsString(page));
        }
    }




}
