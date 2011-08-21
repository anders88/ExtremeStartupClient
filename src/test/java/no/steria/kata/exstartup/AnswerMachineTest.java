package no.steria.kata.exstartup;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class AnswerMachineTest {
	private AnswerMachine answerMachine = new AnswerMachine();

	@Test
	public void shouldAnswerName() throws Exception {
		assertThat(answerMachine.answer("what is your name")).isEqualTo("Anders");
	}
	
	@Test
	public void should_add_numbers() throws Exception {
		assertThat(answerMachine.answer("what is 3 plus 2")).isEqualTo("5");
	}
	
	@Test
	public void shouldMultiply() throws Exception {
		assertThat(answerMachine.answer("20d897c0: what is 6 multiplied by 9")).isEqualTo("54");
		
	}
	
	@Test
	public void shouldFindLargest() throws Exception {
		assertThat(answerMachine.answer("which of the following numbers is the largest: 922, 5")).isEqualTo("922");
		assertThat(answerMachine.answer("which of the following numbers is the largest: 5, 82, 22, 697")).isEqualTo("697");
		assertThat(answerMachine.answer("3cd301a0: which of the following numbers is the largest: 85, 253, 968, 15")).isEqualTo("968");
	}
	
	@Test
	public void should_do_webshop() throws Exception {
		assertThat(answerMachine.answer("what products do you have for sale (comma separated)").split(",")).contains("Apple","Baloon","Banana");
		assertThat(answerMachine.answer("cc3385f0: how many dollars does one Baloon cost")).isEqualTo("5");
		assertThat(answerMachine.answer("20b88220: please put 4 Baloon in my shopping cart")).isEqualTo("Done");
		assertThat(answerMachine.answer("20b88220: please put 5 Apple in my shopping cart")).isEqualTo("Done");
		assertThat(answerMachine.answer("c81453c0: what is my order total")).isEqualTo("75");
	}
}
