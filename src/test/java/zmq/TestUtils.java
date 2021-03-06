/*
    Copyright (c) 2007-2014 Contributors as noted in the AUTHORS file

    This file is part of 0MQ.

    0MQ is free software; you can redistribute it and/or modify it under
    the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation; either version 3 of the License, or
    (at your option) any later version.

    0MQ is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package zmq;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class TestUtils
{
    @Test
    public void testRealloc()
    {
        Integer[] src = new Integer[] {1, 3, 5};
        Integer[] dest = Utils.realloc(Integer.class, src, 3, true);

        assertThat(src.length, is(3));
        assertThat(src, is(dest));

        dest = Utils.realloc(Integer.class, src, 5, true);

        assertThat(dest.length, is(5));
        assertThat(dest[0], is(1));
        assertThat(dest[1], is(3));
        assertThat(dest[2], is(5));
        assertThat(dest[4], nullValue());

        dest = Utils.realloc(Integer.class, src, 6, false);
        assertThat(dest.length, is(6));
        assertThat(dest[0], nullValue());
        assertThat(dest[1], nullValue());
        assertThat(dest[2], nullValue());
        assertThat(dest[3], is(1));
        assertThat(dest[4], is(3));
        assertThat(dest[5], is(5));

        src = new Integer[] {1, 3, 5, 7, 9, 11};
        dest = Utils.realloc(Integer.class, src, 4, false);
        assertThat(dest.length, is(4));
        assertThat(dest[0], is(1));
        assertThat(dest[1], is(3));
        assertThat(dest[2], is(5));
        assertThat(dest[3], is(7));

        dest = Utils.realloc(Integer.class, src, 3, true);
        assertThat(dest.length, is(3));
        assertThat(dest[0], is(7));
        assertThat(dest[1], is(9));
        assertThat(dest[2], is(11));

    }
}
