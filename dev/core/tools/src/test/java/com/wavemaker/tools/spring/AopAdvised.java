/*
 *  Copyright (C) 2009 WaveMaker Software, Inc.
 *
 *  This file is part of the WaveMaker Server Runtime.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.wavemaker.tools.spring;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author small
 * @version $Rev$ - $Date$
 *
 */
public class AopAdvised {
    
    private int ival = 10;

    public int getIval() {
        return ival;
    }
    public void setIval(int ival) {
        this.ival = ival;
    }
    
    public String testUpload(String param1, MultipartFile param2)
            throws IOException {
        return param1+new String(param2.getBytes())+getIval();
    }
}