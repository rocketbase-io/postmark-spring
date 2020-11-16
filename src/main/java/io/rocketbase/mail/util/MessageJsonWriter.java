package io.rocketbase.mail.util;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import io.rocketbase.mail.dto.EmailAttachment;
import io.rocketbase.mail.dto.Header;
import io.rocketbase.mail.dto.Message;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;

public class MessageJsonWriter {

    private static final int BUFFER_SIZE = 3 * 512;

    @SneakyThrows
    public File writeMessageFile(Message msg, EmailAttachment... emailAttachments) {
        File result = File.createTempFile("message", ".json");
        FileWriter fileWriter = new FileWriter(result);
        fileWriter.write("{");
        boolean commaNeeded = false;
        commaNeeded = writeString(fileWriter, commaNeeded, "messageStream", msg.getMessageStream());
        commaNeeded = writeString(fileWriter, commaNeeded, "from", msg.getFrom());
        commaNeeded = writeString(fileWriter, commaNeeded, "to", msg.getTo());
        commaNeeded = writeString(fileWriter, commaNeeded, "cc", msg.getCc());
        commaNeeded = writeString(fileWriter, commaNeeded, "bcc", msg.getBcc());
        commaNeeded = writeString(fileWriter, commaNeeded, "replyTo", msg.getReplyTo());
        commaNeeded = writeString(fileWriter, commaNeeded, "subject", msg.getSubject());
        commaNeeded = writeString(fileWriter, commaNeeded, "htmlBody", msg.getHtmlBody());
        commaNeeded = writeString(fileWriter, commaNeeded, "textBody", msg.getTextBody());
        commaNeeded = writeString(fileWriter, commaNeeded, "tag", msg.getTag());
        commaNeeded = handleHeaders(fileWriter, commaNeeded, msg);
        commaNeeded = writeBoolean(fileWriter, commaNeeded, "trackOpens", msg.getTrackOpens());
        commaNeeded = writeString(fileWriter,
                commaNeeded,
                "trackLinks",
                msg.getTrackLinks() != null ? msg.getTrackLinks()
                        .getValue() : null);
        commaNeeded = handleMeta(fileWriter, commaNeeded, msg);

        writeAttachments(fileWriter, commaNeeded, emailAttachments);

        fileWriter.write("}");
        fileWriter.close();

        return result;
    }

    protected void writeAttachments(FileWriter fileWriter, boolean commaNeeded, EmailAttachment[] emailAttachments) throws IOException {
        if (emailAttachments != null && emailAttachments.length > 0) {

            if (commaNeeded) {
                writeComma(fileWriter);
            }
            fileWriter.write("\"attachments\": [");
            boolean firstLoop = true;
            for (EmailAttachment a : emailAttachments) {
                if (!firstLoop) {
                    writeComma(fileWriter);
                }

                fileWriter.write("{");
                boolean subComma = false;
                subComma = writeString(fileWriter, subComma, "Name", a.getName());
                if (subComma) {
                    writeComma(fileWriter);
                }
                fileWriter.write("\"Content\": \"");
                writeFileContent(fileWriter, a.getFile());
                fileWriter.write("\"");
                subComma = true;
                subComma = writeString(fileWriter,
                        subComma,
                        "ContentType",
                        Files.probeContentType(a.getFile()
                                .toPath()));
                writeString(fileWriter, subComma, "ContentId", a.getContentId());
                fileWriter.write("}");
                firstLoop = false;
            }
            fileWriter.write("]");
        }
    }

    protected boolean handleHeaders(FileWriter fileWriter, boolean commaNeeded, Message msg) throws IOException {
        if (msg.getHeaders() != null && !msg.getHeaders()
                .isEmpty()) {
            if (commaNeeded) {
                writeComma(fileWriter);
            }
            fileWriter.write("\"metadata\": {");
            boolean firstLoop = true;
            for (Map.Entry<String, String> entry : msg.getMetadata()
                    .entrySet()) {
                if (!firstLoop) {
                    writeComma(fileWriter);
                }
                fileWriter.write("\"" + JsonStringEncoder.getInstance()
                        .quoteAsString(entry.getKey()) + "\": \"");
                fileWriter.write(JsonStringEncoder.getInstance()
                        .quoteAsString(entry.getValue()));
                fileWriter.write("\"");
                firstLoop = false;
            }
            fileWriter.write("}");
            return true;
        }
        return commaNeeded;
    }

    protected boolean handleMeta(FileWriter fileWriter, boolean commaNeeded, Message msg) throws IOException {
        if (msg.getMetadata() != null && !msg.getMetadata()
                .isEmpty()) {
            if (commaNeeded) {
                writeComma(fileWriter);
            }
            fileWriter.write("\"headers\": [");
            boolean firstLoop = true;
            for (Header header : msg.getHeaders()) {
                if (header.getName() != null) {
                    if (!firstLoop) {
                        writeComma(fileWriter);
                    }
                    fileWriter.write("\"" + JsonStringEncoder.getInstance()
                            .quoteAsString(header.getName()) + "\": ");
                    if (header.getValue() != null) {
                        fileWriter.write("\"");
                        fileWriter.write(JsonStringEncoder.getInstance()
                                .quoteAsString(header.getValue()));
                        fileWriter.write("\"");
                    } else {
                        fileWriter.write("null");
                    }
                    firstLoop = false;
                }
            }
            fileWriter.write("]");
            return true;
        }
        return commaNeeded;
    }

    @SneakyThrows
    protected boolean writeString(FileWriter fileWriter, boolean commaNeeded, String property, String value) {
        if (property != null && value != null) {
            if (commaNeeded) {
                writeComma(fileWriter);
            }
            fileWriter.write("\"" + property + "\": \"");
            fileWriter.write(JsonStringEncoder.getInstance()
                    .quoteAsString(value));
            fileWriter.write("\"");
            return true;
        }
        return commaNeeded;
    }

    @SneakyThrows
    protected boolean writeBoolean(FileWriter fileWriter, boolean commaNeeded, String property, Boolean value) {
        if (property != null && value != null) {
            if (commaNeeded) {
                writeComma(fileWriter);
            }
            fileWriter.write("\"" + property + "\": ");
            fileWriter.write(value.toString());
            return true;
        }
        return commaNeeded;
    }

    @SneakyThrows
    protected void writeComma(FileWriter fileWriter) {
        fileWriter.write(", ");
    }

    @SneakyThrows
    protected void writeFileContent(FileWriter fileWriter, File file) {
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

        try (InputStream in = new FileInputStream(file)) {
            byte[] bytes = new byte[BUFFER_SIZE];

            int len = 0;

            while ((len = in.read(bytes)) == BUFFER_SIZE) {
                fileWriter.write(encoder.encodeToString(bytes));
            }

            // do not run into error when we have 0 bytes
            if (len > 0) {
                bytes = Arrays.copyOf(bytes, len);
                fileWriter.write(encoder.encodeToString(bytes));
            }
        }
    }
}
