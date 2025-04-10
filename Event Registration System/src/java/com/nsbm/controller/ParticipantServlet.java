

package com.nsbm.controller;

import com.nsbm.dao.ParticipantDAO;
import com.nsbm.model.Participant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/Participant")
@WebServlet(name = "ParticipantServlet", urlPatterns = {"/register","/update","/edit","/update","/delete","/filter"})
public class ParticipantServlet extends HttpServlet {
    private ParticipantDAO participantDAO;

    public void init() {
        participantDAO = new ParticipantDAO();
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                response.sendRedirect("error.jsp");
                return;
            }

            switch (action) {
                case "register":
                    registerParticipant(request, response);

                    break;
                case "update":
                    updateParticipant(request, response);

                    break;
                default:
                    response.sendRedirect("error.jsp");
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

    try {
        if (action == null) {
            listParticipants(request, response); 
            return;
        }

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteParticipant(request, response);

                break;
            case "filter":
                filterParticipants(request, response);
                break;
            default:
                listParticipants(request, response);
                break;
        }
    } catch (Exception ex) {
        throw new ServletException(ex);
    }
}

    private void registerParticipant(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Participant participant = new Participant();
        participant.setName(request.getParameter("name"));
        participant.setEmail(request.getParameter("email"));
        participant.setEvent(request.getParameter("event"));

        participantDAO.insertParticipant(participant);
        response.sendRedirect("participantList.jsp");


    }

    
    private void updateParticipant(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Participant participant = new Participant();
        participant.setId(id);
        participant.setName(request.getParameter("name"));
        participant.setEmail(request.getParameter("email"));
        participant.setEvent(request.getParameter("event"));

        participantDAO.updateParticipant(participant);
        response.sendRedirect("ParticipantServlet");
    }


    private void deleteParticipant(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        participantDAO.deleteParticipant(id);
        response.sendRedirect("ParticipantServlet");
    }

    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Participant participant = participantDAO.getById(id);
        request.setAttribute("participant", participant);
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateForm.jsp");
        dispatcher.forward(request, response);
    }
    

    private void listParticipants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Participant> list = participantDAO.listParticipants();
        request.setAttribute("participantList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("participantList.jsp");
        dispatcher.forward(request, response);

    }

    private void filterParticipants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eventName = request.getParameter("event");
        List<Participant> list = participantDAO.filterByEvent(eventName);
        request.setAttribute("participantList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("participantList.jsp");
        dispatcher.forward(request, response);
    }
}
