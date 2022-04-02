package cl.medical.medicalapp.assembler;

import cl.medical.medicalapp.controller.ConsultationController;
import cl.medical.medicalapp.controller.DoctorController;
import cl.medical.medicalapp.controller.PatientController;
import cl.medical.medicalapp.dto.ConsultationResumeDto;
import cl.medical.medicalapp.model.Consultation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ConsultationResumeAssembler implements RepresentationModelAssembler<Consultation, ConsultationResumeDto> {

    public static final String FIELD_SEPARATOR = " ";

    @Override
    public ConsultationResumeDto toModel(Consultation consultation) {
        return this.toConsultationResumeDto(consultation);
    }

    @Override
    public CollectionModel<ConsultationResumeDto> toCollectionModel(Iterable<? extends Consultation> consultationList) {
        List<ConsultationResumeDto> consultationResumeDtoList = new ArrayList<>();
        consultationList.forEach(consultation -> {
            consultationResumeDtoList.add(this.toConsultationResumeDto(consultation));
        });
        return CollectionModel.of(consultationResumeDtoList);
    }

    private ConsultationResumeDto toConsultationResumeDto(Consultation consultation) {
        ConsultationResumeDto consultationResumeDto = new ConsultationResumeDto();
        consultationResumeDto.setIdConsultation(consultation.getIdConsultation());
        consultationResumeDto.setFullNameDoctor(consultation.getDoctor().getFirstName() + FIELD_SEPARATOR + consultation.getDoctor().getLastName());
        consultationResumeDto.setFullNamePatient(consultation.getPatient().getFirstName() + FIELD_SEPARATOR + consultation.getPatient().getLastName());
        // patient
        Link patientLink = linkTo(methodOn(PatientController.class).findById((consultation.getPatient().getIdPatient()))).withRel("patient");
        consultationResumeDto.add(patientLink);
        // doctor
        Link doctorLink = linkTo(methodOn(DoctorController.class).findById((consultation.getDoctor().getIdDoctor()))).withRel("doctor");
        consultationResumeDto.add(doctorLink);
        // self
        Link selfLink = linkTo(methodOn(ConsultationController.class).findById((consultation.getIdConsultation()))).withSelfRel();
        consultationResumeDto.add(selfLink);
        return consultationResumeDto;
    }

}
