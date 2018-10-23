package utilities;

import org.apache.commons.lang3.StringEscapeUtils;

public class XML {



    public static void main(String[] args) {
//        String original = "&amp;lt;XML&amp;gt;                        &amp;lt;TYPE&amp;gt;0&amp;lt;/TYPE&amp;gt;            &amp;lt;DATATYPE&amp;gt;0&amp;lt;/DATATYPE&amp;gt;                                    &amp;lt;TABLE&amp;gt;view_nbu_slp_image&amp;lt;/TABLE&amp;gt;            &amp;lt;REPORTFIELDS&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; key=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Master Server&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;master_server&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; key=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Image ID&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;image_id&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;SLP Name&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;slp_name&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;SLP Version&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;slp_version&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;String&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Backup Client Name&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;backup_client_name&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Backup Client Type&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;backup_client_type&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Backup Policy Name&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;backup_policy&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; starttimefield=&amp;quot;true&amp;quot; endtimefield=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Backup Time&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;backup_time&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;Date&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Data Classification&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;data_classification&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Origin Master Server&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;origin_master_server&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; starttimefield=&amp;quot;true&amp;quot; endtimefield=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Image Created Time&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;created_time&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;Date&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; starttimefield=&amp;quot;true&amp;quot; endtimefield=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Image Completed Time&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;completed_time&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;Date&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; starttimefield=&amp;quot;true&amp;quot; endtimefield=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;In Process Time&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;in_process_time&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;Date&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Image SLP State&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;state&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;            &amp;lt;/REPORTFIELDS&amp;gt;                    &amp;lt;/XML&amp;gt; ";
//        String s = "&amp;lt;XML&amp;gt;&amp;lt;TYPE&amp;gt;0&amp;lt;/TYPE&amp;gt;&amp;lt;DATATYPE&amp;gt;0&amp;lt;/DATATYPE&amp;gt;&amp;lt;TABLE&amp;gt;view_nbu_slp_image&amp;lt;/TABLE&amp;gt;&amp;lt;REPORTFIELDS&amp;gt;&amp;lt;FIELD cast=&amp;quot;String&amp;quot;condition=&amp;quot;true&amp;quot;display=&amp;quot;true&amp;quot;&amp;gt;&amp;lt;LABEL&amp;gt;Image SLP State&amp;lt;/LABEL&amp;gt;&amp;lt;FIELDNAME&amp;gt;state&amp;lt;/FIELDNAME&amp;gt;&amp;lt;/FIELD&amp;gt;&amp;lt;/REPORTFIELDS&amp;gt;&amp;lt;/XML&amp;gt;";
//        String s = " &lt;XML&gt;                        &lt;TYPE&gt;0&lt;/TYPE&gt;                                    &lt;DATATYPE&gt;0&lt;/DATATYPE&gt;                                    &lt;TABLE&gt;view_nbu_slp_image&lt;/TABLE&gt;            &lt;REPORTFIELDS&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot; key=&quot;true&quot;&gt;                    &lt;LABEL&gt;Master Server&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;master_server&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot; key=&quot;true&quot;&gt;                    &lt;LABEL&gt;Image ID&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;image_id&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;SLP Name&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;slp_name&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;SLP Version&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;slp_version&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;String&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Backup Client Name&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;backup_client_name&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Backup Client Type&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;backup_client_type&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Backup Policy Name&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;backup_policy&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot; starttimefield=&quot;true&quot; endtimefield=&quot;true&quot;&gt;                    &lt;LABEL&gt;Backup Time&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;backup_time&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;Date&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Data Classification&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;data_classification&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Origin Master Server&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;origin_master_server&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot; starttimefield=&quot;true&quot; endtimefield=&quot;true&quot;&gt;                    &lt;LABEL&gt;Image Created Time&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;created_time&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;Date&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot; starttimefield=&quot;true&quot; endtimefield=&quot;true&quot;&gt;                    &lt;LABEL&gt;Image Completed Time&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;completed_time&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;Date&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot; starttimefield=&quot;true&quot; endtimefield=&quot;true&quot;&gt;                    &lt;LABEL&gt;In Process Time&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;in_process_time&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;Date&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Image SLP State&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;state&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;            &lt;/REPORTFIELDS&gt;                    &lt;/XML&gt;   ";
        String s = "  <XML>\n" +
                "            <CATEGORY>Symantec NetBackup</CATEGORY>\n" +
                "            <TYPE>0</TYPE>\n" +
                "            <REPORTCLASS>2</REPORTCLASS>\n" +
                "            <REPORTTYPE>1</REPORTTYPE>\n" +
                "            <DATATYPE>0</DATATYPE>\n" +
                "            <HIDDEN>0</HIDDEN>\n" +
                "            <SYSTEM>0</SYSTEM>\n" +
                "            <TABLE>view_nbu_slp_image</TABLE>\n" +
                "            <REPORTFIELDS>\n" +
                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\" key=\"true\">\n" +
                "                    <LABEL>Master Server</LABEL>\n" +
                "                    <FIELDNAME>master_server</FIELDNAME>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\" key=\"true\">\n" +
                "                    <LABEL>Image ID</LABEL>\n" +
                "                    <FIELDNAME>image_id</FIELDNAME>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
                "                    <LABEL>SLP Name</LABEL>\n" +
                "                    <FIELDNAME>slp_name</FIELDNAME>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\">\n" +
                "                    <LABEL>SLP Version</LABEL>\n" +
                "                    <FIELDNAME>slp_version</FIELDNAME>\n" +
                "                    <DISPLAYCAST>String</DISPLAYCAST>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
                "                    <LABEL>Backup Client Name</LABEL>\n" +
                "                    <FIELDNAME>backup_client_name</FIELDNAME>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
                "                    <LABEL>Backup Client Type</LABEL>\n" +
                "                    <FIELDNAME>backup_client_type</FIELDNAME>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
                "                    <LABEL>Backup Policy Name</LABEL>\n" +
                "                    <FIELDNAME>backup_policy</FIELDNAME>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\" starttimefield=\"true\" endtimefield=\"true\">\n" +
                "                    <LABEL>Backup Time</LABEL>\n" +
                "                    <FIELDNAME>backup_time</FIELDNAME>\n" +
                "                    <DISPLAYCAST>Date</DISPLAYCAST>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
                "                    <LABEL>Data Classification</LABEL>\n" +
                "                    <FIELDNAME>data_classification</FIELDNAME>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
                "                    <LABEL>Origin Master Server</LABEL>\n" +
                "                    <FIELDNAME>origin_master_server</FIELDNAME>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\" starttimefield=\"true\" endtimefield=\"true\">\n" +
                "                    <LABEL>Image Created Time</LABEL>\n" +
                "                    <FIELDNAME>created_time</FIELDNAME>\n" +
                "                    <DISPLAYCAST>Date</DISPLAYCAST>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\" starttimefield=\"true\" endtimefield=\"true\">\n" +
                "                    <LABEL>Image Completed Time</LABEL>\n" +
                "                    <FIELDNAME>completed_time</FIELDNAME>\n" +
                "                    <DISPLAYCAST>Date</DISPLAYCAST>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\" starttimefield=\"true\" endtimefield=\"true\">\n" +
                "                    <LABEL>In Process Time</LABEL>\n" +
                "                    <FIELDNAME>in_process_time</FIELDNAME>\n" +
                "                    <DISPLAYCAST>Date</DISPLAYCAST>\n" +
                "                </FIELD>\n" +
                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
                "                    <LABEL>Image SLP State</LABEL>\n" +
                "                    <FIELDNAME>state</FIELDNAME>\n" +
                "                </FIELD>\n" +
                "            </REPORTFIELDS>\n" +
                "\n" +
                "            <QUERY>\n" +
                "                <EXPRESSION cast=\"String\" op=\"is\" type=\"datamine\">\n" +
                "                    <TABLE nodefield=\"agent_name\" starttimefield=\"@starttimefield@\" endtimefield=\"@endtimefield@\">view_nbu_slp_image</TABLE>\n" +
                "                    <ROWSET>view_nbu_slp_image</ROWSET>\n" +
                "                    <SQL>\n" +
                "                        <SELECT>\n" +
                "                           SELECT * from view_nbu_slp_image WHERE %%conditions%%;\n" +
                "                        </SELECT>\n" +
                "                    </SQL>\n" +
                "                    <CONDSET>view_nbu_slp_image</CONDSET>\n" +
                "                </EXPRESSION>\n" +
                "            </QUERY>\n" +
                "        </XML>";
        Clipboard.clip(encodeXML(s));
    }

    private static String decodeXML(String s1) {
        String s = s1;
        s = s.replaceAll("&amp;", "&");
        s = s.replaceAll("&gt;", ">");
        s = s.replaceAll("&lt;", "<");
        s = s.replaceAll("&quot;", "\"");
        s = s.replaceAll("&apos;", "\'");
        System.out.println(s);
        return s;
    }

    private static String encodeXML(String s1) {
        String s = s1;
        s = s.replaceAll("&", "&amp;");
        s = s.replaceAll(">","&gt;");
        s = s.replaceAll( "<", "&lt;");
        s = s.replaceAll("\"", "&quot;");
        s = s.replaceAll("\'", "&apos;");
        System.out.println(s);
        return s;
    }
}
