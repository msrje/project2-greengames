package com.green.nowon.service.mypage;

import org.springframework.ui.Model;

public interface MyPageService {


	void info(long mno, Model model, Model model2);


	void list(long mno, Model model);

	void salaryInfo(long mno, Model model, Model model2, Model model3);

	void update(long mno, long pno, long dno);

	

	
}
