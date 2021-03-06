package foodwarehouse.core.service;

import foodwarehouse.core.data.complaint.Complaint;
import foodwarehouse.core.data.complaint.ComplaintRepository;
import foodwarehouse.core.data.complaint.ComplaintState;
import foodwarehouse.core.data.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public Optional<Complaint> createComplaint(Order order,  String content) {
        return complaintRepository.createComplaint(order, content);
    }

    public Optional<Complaint> findComplaintById(int complaintId) {
        return complaintRepository.findComplaintById(complaintId);
    }

    public List<Complaint> findComplaints() {
        return complaintRepository.findComplaints();
    }

    public List<Complaint> findCustomerComplaints(int customerId) {
        return complaintRepository.findCustomerComplaints(customerId);
    }

    public void cancelComplaint(int complaintId) {
        complaintRepository.cancelComplaint(complaintId);
    }

    public void addDecisionToComplaint(int complaintId, String decision, ComplaintState complaintState) {
        complaintRepository.addDecisionToComplaint(complaintId, decision, complaintState);
    }

    public List<Complaint> findOrderComplaints(int orderId) {
        return complaintRepository.findOrderComplaints(orderId);
    }
}
