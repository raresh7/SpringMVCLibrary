package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.MenuTab;

@Component
public abstract class GenericController {
	@Autowired
	protected List<MenuTab> tabsList;
}
