
package org.fcrepo.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.fcrepo.utils.FedoraJcrTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"org.slf4j.*", "javax.xml.parsers.*", "org.apache.xerces.*"})
@PrepareForTest({ServiceHelpers.class})
public class ObjectServiceTest implements FedoraJcrTypes {

    private Session mockSession;

    private Node mockRoot;

    @Before
    public void setUp() throws RepositoryException {
        mockSession = mock(Session.class);
        mockRoot = mock(Node.class);
        when(mockSession.getRootNode()).thenReturn(mockRoot);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateObject() throws Exception {
        final Node mockNode = mock(Node.class);
        final String testPath = "/foo";
        when(mockRoot.getNode(testPath.substring(1))).thenReturn(mockNode);
        final ObjectService testObj = new ObjectService();
        final Node actual =
                testObj.createObject(mockSession, testPath).getNode();
        assertEquals(mockNode, actual);
    }

    @Test
    public void testGetObject() throws RepositoryException {
        final Session mockSession = mock(Session.class);
        final Node mockNode = mock(Node.class);
        final String testPath = "/foo";
        when(mockSession.getNode(testPath)).thenReturn(mockNode);
        final ObjectService testObj = new ObjectService();
        testObj.getObject(mockSession, "/foo");
        verify(mockSession).getNode(testPath);
    }

    @Test
    public void testGetObjectNode() throws RepositoryException {
        final Session mockSession = mock(Session.class);
        final String testPath = "/foo";
        final ObjectService testObj = new ObjectService();
        testObj.getObjectNode(mockSession, "/foo");
        verify(mockSession).getNode(testPath);
    }

}
