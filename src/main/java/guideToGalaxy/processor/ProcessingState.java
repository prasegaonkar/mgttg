package guideToGalaxy.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessingState {
	private String aliasOfLiteral_I;
	private String aliasOfLiteral_V;
	private String aliasOfLiteral_X;
	private String aliasOfLiteral_L;
	private String aliasOfLiteral_C;
	private String aliasOfLiteral_D;
	private String aliasOfLiteral_M;
	private Map<String, BigDecimal> metalPerUnitCreditValue;
	private List<String> responses;

	public String getAliasOfLiteral_I() {
		return aliasOfLiteral_I;
	}

	public void setAliasOfLiteral_I(String aliasOfLiteral_I) {
		this.aliasOfLiteral_I = aliasOfLiteral_I;
	}

	public String getAliasOfLiteral_V() {
		return aliasOfLiteral_V;
	}

	public void setAliasOfLiteral_V(String aliasOfLiteral_V) {
		this.aliasOfLiteral_V = aliasOfLiteral_V;
	}

	public String getAliasOfLiteral_X() {
		return aliasOfLiteral_X;
	}

	public void setAliasOfLiteral_X(String aliasOfLiteral_X) {
		this.aliasOfLiteral_X = aliasOfLiteral_X;
	}

	public String getAliasOfLiteral_L() {
		return aliasOfLiteral_L;
	}

	public void setAliasOfLiteral_L(String aliasOfLiteral_L) {
		this.aliasOfLiteral_L = aliasOfLiteral_L;
	}

	public String getAliasOfLiteral_C() {
		return aliasOfLiteral_C;
	}

	public void setAliasOfLiteral_C(String aliasOfLiteral_C) {
		this.aliasOfLiteral_C = aliasOfLiteral_C;
	}

	public String getAliasOfLiteral_D() {
		return aliasOfLiteral_D;
	}

	public void setAliasOfLiteral_D(String aliasOfLiteral_D) {
		this.aliasOfLiteral_D = aliasOfLiteral_D;
	}

	public String getAliasOfLiteral_M() {
		return aliasOfLiteral_M;
	}

	public void setAliasOfLiteral_M(String aliasOfLiteral_M) {
		this.aliasOfLiteral_M = aliasOfLiteral_M;
	}

	public Map<String, BigDecimal> getMetalPerUnitCreditValue() {
		return metalPerUnitCreditValue;
	}

	public void putMetalPerUnitCreditValue(String metalType, BigDecimal perUnitCreditValue) {
		if (metalPerUnitCreditValue == null) {
			metalPerUnitCreditValue = new HashMap<>();
		}
		metalPerUnitCreditValue.put(metalType, perUnitCreditValue);
	}

	public List<String> getResponses() {
		return responses;
	}

	public void addResponse(String response) {
		if (responses == null) {
			responses = new ArrayList<>();
		}
		responses.add(response);
	}

}
