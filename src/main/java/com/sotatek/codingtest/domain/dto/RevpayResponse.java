package com.sotatek.codingtest.domain.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RevpayResponse {
	@JSONField(name = "Revpay_Merchant_ID")
	@NonNull
	private String revpayMerchantId;

	@JSONField(name = "Payment_ID")
	private Integer paymentId;

	@JSONField(name = "Bank_Code")
	private String bankCode;

	@JSONField(name = "Transaction_ID")
	private String transactionId;

	@JSONField(name = "Reference_Number")
	private String referenceNumber;

	@JSONField(name = "Amount")
	private Double amount;

	@JSONField(name = "Currency")
	private String currency;

	@JSONField(name = "Transaction_Description")
	private String transactionDescription;

	@JSONField(name = "Response_Code")
	private String responseCode;

	@JSONField(name = "Error_Description")
	private String errorDescription;

	@JSONField(name = "Settlement_Amount")
	private Double settlementAmount;

	@JSONField(name = "Settlement_Currency")
	private String settlementCurrency;

	@JSONField(name = "Settlement_FX_Rate")
	private Double settlementFxRate;

	@JSONField(name = "Key_Index")
	private Integer keyIndex;

	@JSONField(name = "Signature")
	private String signature;

	@JSONField(name = "Request_Datetime")
	private String requestDatetime;

	@JSONField(name = "Response_Datetime")
	private String responseDatetime;

}
