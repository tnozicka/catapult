package org.kontinuity.catapult.service.openshift.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.junit.rules.ExternalResource;
import org.kontinuity.catapult.service.openshift.api.OpenShiftProject;
import org.kontinuity.catapult.service.openshift.spi.OpenShiftServiceSpi;

/**
 * JUnit rule to delete OpenShift projects at the end of a test.
 */
public class DeleteOpenShiftProjectRule extends ExternalResource {

	private static final Logger log = Logger.getLogger(DeleteOpenShiftProjectRule.class.getName());

	/** the projects to delete. */
	private final Collection<OpenShiftProject> createdProjects = new ArrayList<>();

	/** hook to the OpenShift service to call to delete the projects. */
	private final OpenShiftServiceContainer test;

	/**
	 * Constructor
	 * 
	 * @param test
	 *            the test base which contains an OpenShift service to call to delete the projects.
	 */
	public DeleteOpenShiftProjectRule(final OpenShiftServiceContainer test) {
		this.test = test;
	}

	/**
	 * Adds a project in the list of projects to delete at the end of the test.
	 * 
	 * @param project
	 *            the project to delete
	 */
	public void add(final OpenShiftProject project) {
		createdProjects.add(project);
	}

	@Override
	protected void before() throws Throwable {
		createdProjects.clear();
	}

	@Override
	protected void after() {
		createdProjects.forEach(project -> {
			final String projectName = project.getName();
			final boolean deleted = ((OpenShiftServiceSpi) test.getOpenShiftService()).deleteProject(project);
			log.info("Deleted " + projectName + ": " + deleted);
		});
	}

}
