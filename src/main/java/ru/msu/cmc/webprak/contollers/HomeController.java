package ru.msu.cmc.webprak.contollers;

import ru.msu.cmc.webprak.DAO.*;
import ru.msu.cmc.webprak.DAO.impl.*;
import ru.msu.cmc.webprak.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Controller
public class HomeController {

    @Autowired
    private final DealDAO dealDAO = new DealDAOImplementation();

    @Autowired
    private final ServicesDAO servicesDAO = new ServicesDAOImplementation();

    @Autowired
    private final WorkersDAO workersDAO = new WorkersDAOImplementation();

    @Autowired
    private final ClientsDAO clientsDAO = new ClientsDAOImplementation();

    @RequestMapping(value = { "/", "/services"})
    public String services() {
        return "services";
    }

    public int mode = 0;
    public Long user_id = null;

    public String fullname = "";

    @RequestMapping(value = "index")
    public String about() {
        return "index";
    }

    @RequestMapping(value = "admin_panel")
    public String admin_panel() {
        return "admin_panel";
    }

    @RequestMapping(value = "contracts")
    public String contracts() {
        return "contracts";
    }

    @RequestMapping(value = "clients")
    public String clients() {
        return "clients";
    }

    @RequestMapping(value = "staff")
    public String staff() {
        return "staff";
    }

    @RequestMapping(value = "signin_client")
    public String sign_client() {
        return "signin_client";
    }

    @GetMapping("/edit_client")
    public String edit_client_page(@RequestParam(name = "client_id", required = false) Long clientId, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        if (clientId == null) {
            model.addAttribute("client", new Clients());
            model.addAttribute("clientsDAO", clientsDAO);
            return "edit_client";
        }

        Clients client = clientsDAO.getById(clientId);

        if (client == null) {
            model.addAttribute("error_msg", "В базе нет клиента с ID = " + clientId);
            return "error_page";
        }

        model.addAttribute("client", client);
        model.addAttribute("clientsDAO", clientsDAO);
        return "edit_client";
    }
    @GetMapping("/edit_contract")
    public String edit_contract_page(@RequestParam(name = "contract_id", required = false) Long contractId, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        if (contractId == null) {
            model.addAttribute("contract", new Deal());
            model.addAttribute("contractsDAO", dealDAO);
            return "edit_contract";
        }

        Deal deal = dealDAO.getById(contractId);

        if (deal == null) {
            model.addAttribute("error_msg", "В базе нет контракта с ID = " + contractId);
            return "error_page";
        }

        model.addAttribute("contract", deal);
        model.addAttribute("contractsDAO", dealDAO);
        return "edit_contract";
    }

    @GetMapping("/edit_staff")
    public String edit_staff_page(@RequestParam(name = "staff_id", required = false) Long staffId, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        if (staffId == null) {
            model.addAttribute("staff", new Workers());
            model.addAttribute("staffDAO", workersDAO);
            return "edit_staff";
        }

        Workers staff = workersDAO.getById(staffId);

        if (staff == null) {
            model.addAttribute("error_msg", "В базе нет работника с ID = " +staffId);
            return "error_page";
        }

        model.addAttribute("staff", staff);
        model.addAttribute("staffDAO", workersDAO);
        return "edit_staff";
    }

    @GetMapping("/edit_service")
    public String edit_service_page(@RequestParam(name = "service_id", required = false) Long serviceId, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        if (serviceId == null) {
            model.addAttribute("service", new Services());
            model.addAttribute("servicesDAO", servicesDAO);
            return "edit_service";
        }

        Services service = servicesDAO.getById(serviceId);

        if (service == null) {
            model.addAttribute("error_msg", "В базе нет услуги с ID = " + serviceId);
            return "error_page";
        }

        model.addAttribute("service", service);
        model.addAttribute("servicesDAO", servicesDAO);
        return "edit_service";
    }

    @GetMapping({"/", "/services"})
    public String servicesList(Model model) {
        List<Services> services_list = (List<Services>) servicesDAO.getAll();
        model.addAttribute("people", services_list);
        model.addAttribute("services_list", services_list);
        model.addAttribute("servicesDAO", servicesDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "services";
    }


    @GetMapping("contracts")
    public String contractsListPage(Model model) {
        List<Deal> contracts_list = (List<Deal>) dealDAO.getAll();
        model.addAttribute("contracts_list", contracts_list);
        model.addAttribute("contractsDAO", dealDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "contracts";
    }

    @GetMapping("about_company_page")
    public String get_about(Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "about_company_page";
    }

    @GetMapping("signin_client")
    public String get_signin(Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "signin_client";
    }

    @GetMapping("lk_client")
    public String get_lk_client(Model model) {
        List<Deal> contracts_list = (List<Deal>) dealDAO.getAllDealsByValue("client_id", String.valueOf(user_id));
        model.addAttribute("contracts_list", contracts_list);
        model.addAttribute("contractsDAO", dealDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "lk_client";
    }


    @GetMapping("lk_staff")
    public String get_lk_staff(Model model) {
        List<Deal> contracts_list_old = (List<Deal>) dealDAO.getAll();
        List<Deal> contracts_list = new ArrayList<Deal>();
        /*List<EmployeeRegisteredService> ers_list = new ArrayList<>();
        if (user_id != null) {
            ers_list = employeeRegisteredServiceDAO.getEmployeeRegisteredServiceByValue("employee_id", user_id);
        }
        for (int i = 0; i < contracts_list_old.size(); i++) {
            Long contract_id = contracts_list_old.get(i).getId();
            boolean finded = false;
            for (int j = 0; j < ers_list.size(); j++) {
                Long cid = ers_list.get(j).getId().getRegistered_service_id();
                if (Objects.equals(cid, contract_id)) {
                    finded = true;
                    break;
                }
            }
            if (finded) {
                contracts_list.add(contracts_list_old.get(i));
            }
        }*/
        model.addAttribute("contracts_list", contracts_list);
        model.addAttribute("contractsDAO", dealDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "lk_staff";
    }

    @GetMapping("admin_panel")
    public String get_admin_panel(Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "admin_panel";
    }

    @GetMapping("clients")
    public String get_clients(Model model) {
        List<Clients> clients_list = (List<Clients>) clientsDAO.getAll();
        model.addAttribute("clients_list", clients_list);
        model.addAttribute("clientsDAO", clientsDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "clients";
    }


    @GetMapping("staff")
    public String get_staff(Model model) {
        List<Workers> staff_list = (List<Workers>) workersDAO.getAll();
        model.addAttribute("staff_list", staff_list);
        model.addAttribute("staffDAO", workersDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "staff";
    }

    @PostMapping("/save_client")
    public String save_client(@RequestParam(name = "id_client", required = false) Long id,
                              @RequestParam(name = "company_name") String name,
                              @RequestParam(name = "face") String face,
                              @RequestParam(name = "email", required = false) String email,
                              @RequestParam(name = "login") String login,
                              @RequestParam(name = "password") String password,
                              Model model) {

        Clients client = null;
        if (id != null) {
            client = clientsDAO.getById(id);
        }

        if (client != null) {
            client.setName(name);
            client.setFace(face);
            client.setEmail(email);
            client.setLogin(login);
            client.setPassword(password);
            clientsDAO.update(client);
        } else {
            client = new Clients(id, name, face, email, login, password);
            clientsDAO.save(client);
        }

        return "redirect:/clients";
    }

    @PostMapping("/save_staff")
    public String save_staff(@RequestParam(name = "id_worker", required = false) Long id,
                             @RequestParam(name = "full_name") String name,
                             @RequestParam(name = "address") String address,
                             @RequestParam(name = "phone", required = false) String phone,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "education") String educ,
                             @RequestParam(name = "login") String login,
                             @RequestParam(name = "password", required = false) String password,
                             @RequestParam(name = "post") String post,
                             @RequestParam(name = "is_admin") Long is_admin,
                             Model model) {

        Workers staff = null;
        if (id != null) {
            staff = workersDAO.getById(id);
        }

        if (staff != null) {
            staff.setName(name);
            staff.setAddress(address);
            staff.setPhone(phone);
            staff.setEmail(email);
            staff.setLogin(login);
            staff.setPassword(password);
            staff.setPost(post);
            staff.setEduc(educ);
            staff.setIs_admin(is_admin);
            workersDAO.update(staff);
        } else {
            staff = new Workers(id, name, address, phone, email, educ, post, login, password, is_admin);
            workersDAO.save(staff);
        }

        return "redirect:/staff";
    }

    Date str2Date(String s) {
        return s.isBlank() ? null : Date.valueOf(s);
    }

    /*@PostMapping("/save_contract")
    public String save_contract(@RequestParam(name = "contract_id", required = false) Long contract_id,
                                @RequestParam(name = "client_id") Long client_id,
                                @RequestParam(name = "service_type_id") Long service_type_id,
                                @RequestParam(name = "beginning_date", required = false) String beginning_date,
                                @RequestParam(name = "date_of_completion", required = false) String date_of_completion,
                                @RequestParam(name = "contract_description") String contract_description,
                                @RequestParam(name = "real_cost") Long real_cost,
                                Model model) {

        Contracts contract = null;
        if (contract_id != null) {
            contract = contractsDAO.getById(contract_id);
        }

        if (contract != null) {
            contract.setClient_id(client_id);
            contract.setService_type_id(service_type_id);
            contract.setBeginning_date(str2Date(beginning_date));
            contract.setDate_of_completion(str2Date(date_of_completion));
            contract.setContract_description(contract_description);
            contract.setReal_cost(real_cost);
            contractsDAO.update(contract);
        } else {
            contract = new Contracts(contract_id, client_id, service_type_id, str2Date(beginning_date), str2Date(date_of_completion), contract_description, real_cost);
            contractsDAO.save(contract);
        }

        return "redirect:/contracts";
    }
*/

    @PostMapping("/save_service")
    public String save_services(@RequestParam(name = "id_service", required = false) Long id,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "price") Long price,
                                @RequestParam(name = "describe", required = false) String descr,
                                Model model) {

        Services service = null;
        if (id != null) {
            service = servicesDAO.getById(id);
        }

        if (service != null) {
            service.setName(name);
            service.setDescr(descr);
            service.setPrice(price);
            servicesDAO.update(service);
        } else {
            service = new Services(id, name, price, descr);
            servicesDAO.save(service);
        }

        return "redirect:/services";
    }
    @PostMapping("/delete_client")
    public String removeClient(@RequestParam(name = "client_id") Long clientId) {
        clientsDAO.deleteById(clientId);
        return "redirect:/clients";
    }

    @PostMapping("/delete_contract")
    public String removeContract(@RequestParam(name = "contract_id") Long contractId) {
        dealDAO.deleteById(contractId);
        return "redirect:/contracts";
    }

    @PostMapping("/delete_staff")
    public String removeStaff(@RequestParam(name = "staff_id") Long staffId) {
        workersDAO.deleteById(staffId);
        return "redirect:/staff";
    }

    @PostMapping("/delete_service")
    public String removeService(@RequestParam(name = "service_id") Long serviceId) {
        servicesDAO.deleteById(serviceId);
        return "redirect:/services";
    }

    @PostMapping("/signin_as_guest")
    public String signin_as_guest() {
        mode = 3;
        user_id = null;
        return "redirect:/services";
    }

}