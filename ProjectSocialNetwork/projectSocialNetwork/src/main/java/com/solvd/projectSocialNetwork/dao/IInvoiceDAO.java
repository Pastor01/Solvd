package com.solvd.projectSocialNetwork.dao;

import java.util.ArrayList;


import com.solvd.projectSocialNetwork.model.Invoices;


public interface IInvoiceDAO extends IGenericDAO<Invoices>{

	public ArrayList<Invoices> getCitiesBySubscriptionId(long id);
}
