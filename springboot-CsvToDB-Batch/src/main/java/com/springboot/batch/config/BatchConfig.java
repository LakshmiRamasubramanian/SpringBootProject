package com.springboot.batch.config;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.springboot.batch.model.Product;

@Configuration
public class BatchConfig {
    @Autowired
	DataSource datasource;
    
    @Autowired
    private JobRepository jobRepository ;
    
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    
    @Bean
    public Job job() {
    	return new JobBuilder("job-1", jobRepository).flow(step()).end().build();
    }
    
    
    @Bean
    public Step step() {
    	StepBuilder stepBuilder = new StepBuilder("step-1", jobRepository);
    	TaskletStep builder = stepBuilder.<Product,Product>chunk(4, platformTransactionManager)
    			.reader(reader()).processor(processor()).writer(writer()).build();
        return builder;
    }
    
    
    
	@Bean
	public ItemReader<Product> reader(){
		FlatFileItemReader<Product> flatFileItemReader = new FlatFileItemReader<Product>();
	 	flatFileItemReader.setResource(new ClassPathResource("ProductCSV.csv"));
		
		DefaultLineMapper<Product> defaultLineMapper = new DefaultLineMapper<Product>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames("id","name","description","price");
		
		BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<Product>();
		fieldSetMapper.setTargetType(Product.class);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		flatFileItemReader.setLineMapper(defaultLineMapper);
		
		return flatFileItemReader;
		
		
	}
	
	@Bean 
	public ItemProcessor<Product,Product> processor() {
		return (p) -> {
			p.setPrice(p.getPrice()-p.getPrice()*10/100);
			return p;
			 
		};
	}
	
	public ItemWriter<Product> writer() {
		
		JdbcBatchItemWriter<Product> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
		jdbcBatchItemWriter.setDataSource(datasource);
		jdbcBatchItemWriter.setSql("insert into product(id,name,description,price)values(:id,:name,:description,:price)");
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
		jdbcBatchItemWriter.afterPropertiesSet();
		return jdbcBatchItemWriter;
		
	}

		
	
	
}
