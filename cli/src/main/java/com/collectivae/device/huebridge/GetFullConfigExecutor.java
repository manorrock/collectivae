/*
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, 
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *   3. Neither the name of the copyright holder nor the names of its 
 *      contributors may be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.collectivae.device.huebridge;

import com.collectivae.cli.CliExecutor;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * The executor to get the full configuration from the Hue Bridge.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class GetFullConfigExecutor implements CliExecutor {

    /**
     * Execute.
     *
     * @param arguments the arguments.
     * @return the result.
     */
    @Override
    public String execute(List<String> arguments) {
        String result;
        Options options = new Options();
        options.addOption(null, "help", false, "Print this message");
        options.addRequiredOption(null, "base-url", true, "The URL of the bridge");
        options.addRequiredOption(null, "username", true, "The username to authenticate with");
        DefaultParser parser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, arguments.toArray(new String[0]));
        } catch (ParseException pe) {
        }
        if (commandLine == null || commandLine.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            StringWriter stringWriter = new StringWriter();
            formatter.printUsage(new PrintWriter(stringWriter), 80,
                    "co device huebridge get-full-config", options);
            formatter.printOptions(new PrintWriter(stringWriter), 80, options, 1, 1);
            result = stringWriter.toString();
        } else {
            HueBridge bridge = new HueBridge();
            bridge.setBaseUrl(commandLine.getOptionValue("base-url"));
            bridge.setUsername(commandLine.getOptionValue("username"));
            JsonParser jsonParser = Json.createParser(new StringReader(bridge.getFullConfig()));
            jsonParser.next();
            JsonObject jsonObject = jsonParser.getObject();
            StringWriter stringWriter = new StringWriter();
            HashMap<String, String> config = new HashMap<>();
            config.put(JsonGenerator.PRETTY_PRINTING, "");
            JsonWriter jsonWriter = Json.createWriterFactory(config).createWriter(stringWriter);
            jsonWriter.writeObject(jsonObject);
            result = stringWriter.toString();
        }
        return result;
    }
}
