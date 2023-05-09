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

    public int mode = 3;
    public Long user_id = null;

    public String fullname = "";

    @RequestMapping(value = "index")
    public String index() {
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
    public String signin_client() {
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

    @GetMapping("index")
    public String get_about(Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "index";
    }

    @GetMapping("signin_client")
    public String get_signin(Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "signin_client";
    }

    @GetMapping("lk_client")
    public String get_lk_client(Model model) {
        if (user_id != null) {
            List<Deal> contracts_list = new ArrayList<Deal>();
            contracts_list = (List<Deal>) dealDAO.getAllDealsByValue("client", String.valueOf(user_id));
            model.addAttribute("contracts_list", contracts_list);
            model.addAttribute("contractsDAO", dealDAO);
            model.addAttribute("mode", mode);
            model.addAttribute("user_id", user_id);
        }

        return "lk_client";
    }


    @GetMapping("lk_staff")
    public String get_lk_staff(Model model) {
        List<Deal> contracts_list = (List<Deal>) dealDAO.getAll();

        //List<Deal> ers_list = new ArrayList<>();
        if (user_id != null) {
            contracts_list = dealDAO.getAllDealsByValue("worker", String.valueOf(user_id));
        }
        /*for (int i = 0; i < contracts_list_old.size(); i++) {
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

        return "redirect:/index";
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

    @PostMapping("/save_contract")
    public String save_contract(@RequestParam(name = "id_deal", required = false) Long contract_id,
                                @RequestParam(name = "client") Long client_id,
                                @RequestParam(name = "worker") Long worker_id,
                                @RequestParam(name = "service") Long service_id,
                                @RequestParam(name = "start_date", required = false) String start_date,
                                @RequestParam(name = "end_date", required = false) String end_date,
                                @RequestParam(name = "description") String description,
                                Model model) {

        Deal contract = null;
        if (contract_id != null) {
            contract = dealDAO.getById(contract_id);
        }

        Clients client = null;
        if (client_id != null) {
            client = clientsDAO.getById(client_id);
        }

        Workers workers = null;
        if (worker_id != null) {
            workers = workersDAO.getById(worker_id);
        }

        Services services = null;
        if (service_id != null) {
            services = servicesDAO.getById(service_id);
        }

        if (contract != null) {
            contract.setClient(client);
            contract.setWorker(workers);
            contract.setService(services);
            contract.setStart(str2Date(start_date));
            contract.setEnd(str2Date(end_date));
            contract.setDescr(description);
            dealDAO.update(contract);
        } else {
            contract = new Deal(contract_id, client, workers, services, str2Date(start_date), str2Date(end_date), description);
            dealDAO.save(contract);
        }

        return "redirect:/lk_staff";
    }


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
        return "redirect:/lk_staff";
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

    @PostMapping("/signin_client")
    public String signin_client(@RequestParam(name = "user_login") String user_login,
                                   @RequestParam(name = "user_password") String user_password) {
        List<Clients> cl = clientsDAO.getAllClientsByValue("login", user_login);
        if (cl.size() != 1) {
            return "redirect:/signin_client";
        }
        if (!cl.get(0).getPassword().equals(user_password)) {
            return "redirect:/signin_client";
        }
        mode = 2;
        user_id = cl.get(0).getId();
        return "redirect:/services";
    }

    @PostMapping("/signin_as_employee")
    public String signin_as_employee(@RequestParam(name = "employee_login") String user_login,
                                     @RequestParam(name = "employee_password") String user_password) {
        List<Workers> sl = workersDAO.getAllWorkersByValue("login", user_login);
        if (sl.size() != 1) {
            return "redirect:/signin_client";
        }
        if (!sl.get(0).getPassword().equals(user_password)) {
            return "redirect:/signin_client";
        }
        if (sl.get(0).getIs_admin() == 1L) {
            mode = 0;
        } else {
            mode = 1;
        }
        user_id = sl.get(0).getId();
        return "redirect:/services";
    }

    @GetMapping("/add_contract")
    public String add_contract_page(Model model) {
        model.addAttribute("add", true);
        model.addAttribute("edit", false);
        model.addAttribute("action", "/add_contract");
        return "add_contract";
    }

    @PostMapping("/add_contract")
    public String add_contract(@RequestParam(name = "id_deal", required = false) Long contract_id,
                @RequestParam(name = "client") Long client_id,
                @RequestParam(name = "worker") Long worker_id,
                @RequestParam(name = "service") Long service_id,
                @RequestParam(name = "start_date", required = false) String start_date,
                @RequestParam(name = "end_date", required = false) String end_date,
                @RequestParam(name = "description") String description,
                Model model) {

            Deal contract = null;
            if (contract_id != null) {
                contract = dealDAO.getById(contract_id);
            }

            Clients client = null;
            if (client_id != null) {
                client = clientsDAO.getById(client_id);
            }

            Workers workers = null;
            if (worker_id != null) {
                workers = workersDAO.getById(worker_id);
            }

            Services services = null;
            if (service_id != null) {
                services = servicesDAO.getById(service_id);
            }

            if (contract != null) {
                contract.setClient(client);
                contract.setWorker(workers);
                contract.setService(services);
                contract.setStart(str2Date(start_date));
                contract.setEnd(str2Date(end_date));
                contract.setDescr(description);
                dealDAO.update(contract);
            } else {
                contract = new Deal(contract_id, client, workers, services, str2Date(start_date), str2Date(end_date), description);
                dealDAO.save(contract);
            }

            return "redirect:/lk_staff";
    }
}
