//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.

//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.

//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package com.app.eskry.imeireset;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by root on 25.03.17.
 */

public class ImeiData {
    public static ImeiPack getData(Context context){
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        ImeiPack imeiPack = new ImeiPack();
        try {

            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());
            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getFirstMethod = telephonyClass.getMethod("getDeviceId", parameter);
            Log.d("SimData", getFirstMethod.toString());
            Object[] obParameter = new Object[1];
            obParameter[0] = 0;
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String first = (String) getFirstMethod.invoke(telephony, obParameter);
            Log.d("SimData", "first :" + first);
            imeiPack.first = first;
            obParameter[0] = 1;
            String second = (String) getFirstMethod.invoke(telephony, obParameter);
            Log.d("SimData", "Second :" + second);
            imeiPack.second = second;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imeiPack;
    }
}
