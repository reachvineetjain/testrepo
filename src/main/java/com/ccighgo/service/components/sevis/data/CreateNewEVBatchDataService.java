package com.ccighgo.service.components.sevis.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.springframework.stereotype.Component;

import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.transport.sevis.BatchParam;
import com.google.common.base.Preconditions;

import gov.ice.xmlschema.sevisbatch.common.NameType;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.AddSiteOfActivity;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.EVPersonType.FinancialInfo;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.EVPersonType.SubjectField;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.NonImgBioType;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.CreateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SOA;
import gov.ice.xmlschema.sevisbatch.table.BirthCntryCodeType;
import gov.ice.xmlschema.sevisbatch.table.CntryCodeWithoutType;
import gov.ice.xmlschema.sevisbatch.table.EVGenderCodeType;

@Component
public class CreateNewEVBatchDataService implements IEVBatchDataService {

	@Override
	public SEVISBatchCreateUpdateEV fetchBatchData(BatchParam batchParam) {
		String batchId = SevisUtils.createBatchId();
		SEVISBatchCreateUpdateEV batch = createCreateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);

		ExchangeVisitor ev = createNewExchangeVisitor(batchParam.getUserId(), "N0000000000", "1");

		/*
		 * NOTE: follow the sequence of elements while adding content to EV.
		 * Refer to XSD.
		 */
		ev.getContent().add(createBio());
		ev.getContent().add(createPositionCode((short) 123));
		ev.getContent().add(createProgramStartDate(getYearMonthDay(LocalDate.now())));
		ev.getContent().add(createProgramEndDate(getYearMonthDay(LocalDate.now())));
		ev.getContent().add(createCategoryCode("1A"));

		ev.getContent().add(createOccupationCategoryCode("01"));

		SubjectField subjectField = createSubjectField("01.0000", "Remarks");
		JAXBElement<SubjectField> sf = wrapAsJaxb(subjectField, SubjectField.class, "SubjectField");
		ev.getContent().add(sf);

		FinancialInfo finance = createFinancialInfo(false);
		JAXBElement<FinancialInfo> fi = wrapAsJaxb(finance, FinancialInfo.class, "FinancialInfo");
		ev.getContent().add(fi);

		AddSiteOfActivity addSoa = createAddSiteOfActivity(createSOA("address 1", "60169", "Site Name", true));
		JAXBElement<AddSiteOfActivity> addSoaJaxb = wrapAsJaxb(addSoa, AddSiteOfActivity.class, "AddSiteOfActivity");
		ev.getContent().add(addSoaJaxb);

		List<ExchangeVisitor> evs = new ArrayList<>();
		evs.add(ev);
		batch.getCreateEV().getExchangeVisitor().addAll(evs);

		return batch;
	}

	private JAXBElement<String> createOccupationCategoryCode(String code) {
		return wrapAsJaxb(code, String.class, "OccupationCategoryCode");
		// return new JAXBElement<String>(new QName("",
		// "OccupationCategoryCode"), String.class, code);
	}

	/**
	 * Creates {@link SubjectField} with minimal required data.
	 * 
	 * @param subFieldCode
	 * @param remark
	 * @return
	 */
	private SubjectField createSubjectField(String subFieldCode, String remark) {
		SubjectField sf = new SubjectField();
		sf.setSubjectFieldCode(subFieldCode);
		sf.setRemarks(remark);

		return sf;
	}

	/**
	 * Creates {@link FinancialInfo} with minimal required elements.
	 * 
	 * @param receivedUSGovtFunds
	 * @return
	 */
	private FinancialInfo createFinancialInfo(boolean receivedUSGovtFunds) {
		FinancialInfo financialInfo = new FinancialInfo();
		financialInfo.setReceivedUSGovtFunds(receivedUSGovtFunds);
		return financialInfo;
	}

	/**
	 * Creates {@link AddSiteOfActivity} with minimal required elements.
	 * 
	 * @param soa
	 * @return
	 */
	private AddSiteOfActivity createAddSiteOfActivity(SOA soa) {
		AddSiteOfActivity addSoa = new AddSiteOfActivity();
		addSoa.getSiteOfActivity().add(soa);
		return addSoa;
	}

	/**
	 * Creates {@link SOA} with minimal required elements.
	 * 
	 * @param address1
	 * @param postalCode
	 * @param siteName
	 * @param primarySite
	 * @return
	 */
	private SOA createSOA(String address1, String postalCode, String siteName, boolean primarySite) {
		SOA soaa = new SOA();
		soaa.setAddress1(address1);
		soaa.setPostalCode(postalCode);
		soaa.setSiteName(siteName);
		soaa.setPrimarySite(primarySite);
		return soaa;
	}

	private <T> JAXBElement<T> wrapAsJaxb(T data, Class<T> klass, String uriLocalPart) {
		return new JAXBElement<T>(new QName("", uriLocalPart), klass, data);
	}

	private <T> JAXBElement<T> wrapAsJaxb(T data, Class<T> klass, String namespaceUri, String uriLocalPart) {
		return new JAXBElement<T>(new QName(namespaceUri, uriLocalPart), klass, data);
	}

	private JAXBElement<NonImgBioType> createBio() {
		NonImgBioType bio = new NonImgBioType();

		NameType fullName = new NameType();
		fullName.setFirstName("First Name");
		fullName.setLastName("Last Name");
		bio.setFullName(fullName);

		bio.setBirthDate(SevisUtils.convert(LocalDate.now()));

		bio.setGender(EVGenderCodeType.F);
		bio.setBirthCity("Birth City");

		bio.setBirthCountryCode(BirthCntryCodeType.AA);
		bio.setCitizenshipCountryCode(CntryCodeWithoutType.AA);
		bio.setPermanentResidenceCountryCode(CntryCodeWithoutType.AA);

		return wrapAsJaxb(bio, NonImgBioType.class, "Biographical");
	}

	private JAXBElement<Short> createPositionCode(Short code) {
		return wrapAsJaxb(code, Short.class, "PositionCode");
	}

	private JAXBElement<String> createCategoryCode(String code) {
		return wrapAsJaxb(code, String.class, "CategoryCode");
	}

	private JAXBElement<XMLGregorianCalendar> createProgramStartDate(XMLGregorianCalendar date) {
		return wrapAsJaxb(date, XMLGregorianCalendar.class, "PrgStartDate");
	}

	private JAXBElement<XMLGregorianCalendar> createProgramEndDate(XMLGregorianCalendar date) {
		return wrapAsJaxb(date, XMLGregorianCalendar.class, "PrgEndDate");
	}

	public ExchangeVisitor createNewExchangeVisitor(String userId, String sevisId, String requestId) {
		Preconditions.checkArgument(userId != null && !userId.isEmpty());
		Preconditions.checkArgument(sevisId != null && !sevisId.isEmpty());
		Preconditions.checkArgument(requestId != null && !requestId.isEmpty());

		ExchangeVisitor ev = new ExchangeVisitor();
		ev.setRequestID(requestId);
		ev.setUserID(userId);

		return ev;
	}

	private XMLGregorianCalendar getYearMonthDay(LocalDate date) {
		try {
			XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			LocalDate d = LocalDate.now();
			cal.setYear(d.getYear());
			cal.setMonth(d.getMonthValue());
			cal.setDay(d.getDayOfMonth());

			return cal;
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
