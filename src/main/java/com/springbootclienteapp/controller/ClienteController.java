package com.springbootclienteapp.controller;

import com.springbootclienteapp.entity.Ciudad;
import com.springbootclienteapp.entity.Cliente;
import com.springbootclienteapp.service.ICiudadService;
import com.springbootclienteapp.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {
    private final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ICiudadService ciudadService;

    @GetMapping("/")
    public String listarCliente(Model model) {
        List<Cliente> listadoClientes = clienteService.listrtodos();
        model.addAttribute("titulo", "Lista de Clientes");
        model.addAttribute("clientes", listadoClientes);
        return "/views/clientes/listar";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        Cliente cliente = new Cliente();
        List<Ciudad> listaCiudades = ciudadService.listaCiudades();
        LOGGER.info("Lista de ciudades:{}" + listaCiudades);
        model.addAttribute("titulo", "Farmulario: Nuevo Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("ciudades", listaCiudades);
        return "/views/clientes/frmCrear";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model,
                          RedirectAttributes attributes) {
        List<Ciudad> listaCiudades = ciudadService.listaCiudades();
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Farmulario: Nuevo Cliente");
            model.addAttribute("cliente", cliente);
            model.addAttribute("ciudades", listaCiudades);
            System.out.println("Existieron errore en el formulario");
            return "/views/clientes/frmCrear";
        }
        clienteService.guardar(cliente);
        attributes.addFlashAttribute("success", "Cliente guardado con exito!");
        return "redirect:/views/clientes/";

    }

    @GetMapping("/edit/{id}")
    public String crear(@PathVariable("id") Long idCliente, Model model, RedirectAttributes attributes) {
        Cliente cliente = null;

        if (idCliente > 0) {
            cliente = clienteService.buscarPorId(idCliente);
            if (cliente == null) {
                System.out.println("Error: El ID del cliente no existe!");
                attributes.addFlashAttribute("error", "ATENCIÓN: El ID del cliente no existe!");
                return "redirect:/views/clientes/";
            }

        } else {
            System.out.println("Error: con el ID del cliente!");
            attributes.addFlashAttribute("error", "ATENCIÓN: Error con el ID del cliente!");
            return "redirect:/views/clientes/";
        }
        List<Ciudad> listaCiudades = ciudadService.listaCiudades();
        LOGGER.info("Lista de ciudades:{}" + listaCiudades);
        model.addAttribute("titulo", "Farmulario: Editar Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("ciudades", listaCiudades);
        return "/views/clientes/frmCrear";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Long idCliente, RedirectAttributes attributes) {
        Cliente cliente = null;

        if (idCliente > 0) {
            cliente = clienteService.buscarPorId(idCliente);
            if (cliente == null) {
                System.out.println("Error: El ID del cliente no existe!");
                attributes.addFlashAttribute("error", "ATENCIÓN: El ID del cliente no existe!");
                return "redirect:/views/clientes/";
            }

        } else {
            System.out.println("Error: con el ID del cliente!");
            attributes.addFlashAttribute("error", "ATENCIÓN: El ID del cliente no existe!");
            return "redirect:/views/clientes/";
        }

        clienteService.elimina(idCliente);
        attributes.addFlashAttribute("error", "WARNING: Registro Eliminado con Exito!");
        return "redirect:/views/clientes/";
    }


}
