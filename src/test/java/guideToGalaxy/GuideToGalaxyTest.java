package guideToGalaxy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GuideToGalaxyTest {
	@Test
	public void noIdeaResponseToNullQuery() {
		String input = null;
		QueryResponder responder = new QueryResponder();
		String response = responder.responseTo(input);
		assertThat(response).isEqualTo("I have no idea what you are talking about");
	}
}
