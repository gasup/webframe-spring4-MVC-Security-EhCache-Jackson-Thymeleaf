package com.newhouse.wframe.common.base;

import java.io.Serializable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IResponseBase<T, ID extends Serializable> 
	extends PagingAndSortingRepository<T, ID>{

}
