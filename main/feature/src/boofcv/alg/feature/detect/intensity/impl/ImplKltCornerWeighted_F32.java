/*
 * Copyright (c) 2011-2012, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://www.boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.alg.feature.detect.intensity.impl;

import boofcv.alg.feature.detect.intensity.KltCornerIntensity;
import boofcv.struct.image.ImageFloat32;

/**
 * <p>
 * Implementation of {@link boofcv.alg.feature.detect.intensity.KltCornerIntensity}
 * that samples pixels using a Gaussian distribution based off of {@link ImplSsdCornerWeighted_F32}.
 * </p>
 *
 * @author Peter Abeles
 */
public class ImplKltCornerWeighted_F32 extends ImplSsdCornerWeighted_F32
		implements KltCornerIntensity<ImageFloat32>
{
	public ImplKltCornerWeighted_F32(int radius) {
		super(radius);
	}

	@Override
	protected float computeResponse() {
		// compute the smallest eigenvalue
		float left = (totalXX + totalYY) * 0.5f;
		float b = (totalXX - totalYY) * 0.5f;
		double right = Math.sqrt(b * b + totalXY * totalXY);
		
		// the smallest eigenvalue will be minus the right side
		return (float)(left - right);
	}
}