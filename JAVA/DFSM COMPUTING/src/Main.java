import java.util.Arrays;
import java.util.List;

import ac.il.afeka.Submission.Submission;
import ac.il.afeka.fsm.DFSM;

public class Main implements Submission, Assignment1 {
	public static void main(String[] args) throws Exception{
		Main main = new Main();
		String dfsmEncode = "0 1/a b/0 , a , 0; 0,b, 1 ;1, a, 0 ; 1, b, 1/0/ 1";
		System.out.println(main.compute(dfsmEncode, "ab"));
	}

	@Override
	public List<String> submittingStudentIds() {
		return Arrays.asList("318323391", "316554260","316080365");
	}

	@Override
	public boolean compute(String dfsmEncoding, String input) throws Exception {
		DFSM dfsm = new DFSM(dfsmEncoding);
		return dfsm.compute(input);
	}

}
