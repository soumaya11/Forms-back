package com.example.pfe.message;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class NotFoundResponse {
	private  String message ;
    public NotFoundResponse(String string) {
		this.message=string;
	}

	
}
