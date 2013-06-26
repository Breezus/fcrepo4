
package org.fcrepo.test.util;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;

import com.sun.jersey.api.uri.UriComponent;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class PathSegmentImpl implements PathSegment {

    // private static final PathSegment EMPTY_PATH_SEGMENT = new
    // PathSegmentImpl("", false);
    private final String path;

    private final MultivaluedMap<String, String> matrixParameters;

    PathSegmentImpl(final String path, final boolean decode) {
        this(path, decode, new MultivaluedMapImpl());
    }

    PathSegmentImpl(final String path, final boolean decode,
            final MultivaluedMap<String, String> matrixParameters) {
        this.path =
                (decode) ? UriComponent.decode(path,
                        UriComponent.Type.PATH_SEGMENT) : path;
        this.matrixParameters = matrixParameters;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public MultivaluedMap<String, String> getMatrixParameters() {
        return matrixParameters;
    }

    public static List<PathSegment> createPathList(final String... strings) {
        final ArrayList<PathSegment> result =
                new ArrayList<PathSegment>(strings.length);
        for (final String string : strings) {
            result.add(new PathSegmentImpl(string, false));
        }
        return result;
    }
}
