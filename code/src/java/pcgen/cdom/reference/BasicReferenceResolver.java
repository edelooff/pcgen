/*
 * Copyright 2010 (C) Tom Parker <thpr@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pcgen.cdom.reference;

import pcgen.cdom.base.Loadable;
import pcgen.util.Logging;

public class BasicReferenceResolver<T extends Loadable> implements
		ReferenceResolver<T>
{
	public void resolve(ReferenceManufacturer<T> rm, String name,
			CDOMSingleRef<T> value)
	{
		T activeObj = rm.getObject(name);
		if (activeObj == null && rm.containsUnconstructed(name))
		{
			activeObj = rm.buildObject(name);
		}
		if (activeObj == null)
		{
			Logging.errorPrint("Unable to Resolve: "
					+ rm.getReferenceDescription() + " " + name);
		}
		else
		{
			value.addResolution(activeObj);
		}
	}

}
