package edu.Vehicles.dto.response;

import java.util.List;
import org.springframework.data.domain.Page;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PagedResponseDto<T> {

	private List<T> content;
	private int pagenumber;
	private int size;
	private long totalElements;
	private int totalpages;
	
	public static<T> PagedResponseDto<T> buildPage(Page<T> page){
		return PagedResponseDto.<T>builder().content(page.getContent())
				.pagenumber(page.getNumber()).size(page.getSize())
				.totalElements(page.getTotalElements())
				.totalpages(page.getTotalPages())
				.build();
	}
	
}
