//package com.ccighgo.service.test.utility;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ccighgo.db.entities.GoIdSequence;
//import com.ccighgo.db.entities.HostFamilyInquiry;
//import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
//import com.ccighgo.jpa.repositories.HostFamilyInquiryRepository;
//
///**
// * 
// * @author CreoDeveloper
// *
// */
//// @RunWith(SpringJUnit4ClassRunner.class)
//// @ContextConfiguration("applicationContext.xml")
//public class TestHostFamily {
//
//   @Autowired HostFamilyInquiryRepository hostFamilyInquiryRepository;
//   @Autowired GoIdSequenceRepository goIdSequenceRepository;
//
//   // @Test
//   public void test() {
//      HostFamilyInquiry pa = new HostFamilyInquiry();
//      pa.setAddress("819 somerset ");
//      pa.setCciComments("comment");
//      pa.setCurrentCity("city");
//      // TODO pa.setCurrentState(HostFamilyData.getState());
//      pa.setFirstName("ahmed");
//      pa.setEmailAddress("ahmed.amer.samir@gmail.com");
//      pa.setLastName("abdelmaaboud");
//      pa.setZipCode("60133");
//      pa.setPreferredPhoneNumber("630-488-0523");
//      pa.setOptionalPhoneNumber("");
//
//      GoIdSequence goIdSequence = new GoIdSequence();
//      goIdSequence = goIdSequenceRepository.save(goIdSequence);
//      pa.setHostFamilyInquiryId(goIdSequence.getGoId());
//      hostFamilyInquiryRepository.saveAndFlush(pa);
//
//   }
//}
