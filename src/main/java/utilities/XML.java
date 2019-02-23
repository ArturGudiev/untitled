package utilities;

import org.apache.commons.lang3.StringEscapeUtils;

public class XML {



    public static void main(String[] args) {
//        String original = "&amp;lt;XML&amp;gt;                        &amp;lt;TYPE&amp;gt;0&amp;lt;/TYPE&amp;gt;            &amp;lt;DATATYPE&amp;gt;0&amp;lt;/DATATYPE&amp;gt;                                    &amp;lt;TABLE&amp;gt;view_nbu_slp_image&amp;lt;/TABLE&amp;gt;            &amp;lt;REPORTFIELDS&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; key=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Master Server&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;master_server&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; key=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Image ID&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;image_id&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;SLP Name&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;slp_name&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;SLP Version&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;slp_version&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;String&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Backup Client Name&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;backup_client_name&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Backup Client Type&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;backup_client_type&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Backup Policy Name&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;backup_policy&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; starttimefield=&amp;quot;true&amp;quot; endtimefield=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Backup Time&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;backup_time&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;Date&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Data Classification&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;data_classification&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Origin Master Server&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;origin_master_server&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; starttimefield=&amp;quot;true&amp;quot; endtimefield=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Image Created Time&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;created_time&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;Date&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; starttimefield=&amp;quot;true&amp;quot; endtimefield=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Image Completed Time&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;completed_time&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;Date&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;Int&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot; starttimefield=&amp;quot;true&amp;quot; endtimefield=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;In Process Time&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;in_process_time&amp;lt;/FIELDNAME&amp;gt;                    &amp;lt;DISPLAYCAST&amp;gt;Date&amp;lt;/DISPLAYCAST&amp;gt;                &amp;lt;/FIELD&amp;gt;                &amp;lt;FIELD cast=&amp;quot;String&amp;quot; condition=&amp;quot;true&amp;quot; display=&amp;quot;true&amp;quot;&amp;gt;                    &amp;lt;LABEL&amp;gt;Image SLP State&amp;lt;/LABEL&amp;gt;                    &amp;lt;FIELDNAME&amp;gt;state&amp;lt;/FIELDNAME&amp;gt;                &amp;lt;/FIELD&amp;gt;            &amp;lt;/REPORTFIELDS&amp;gt;                    &amp;lt;/XML&amp;gt; ";
//        String s = "&amp;lt;XML&amp;gt;&amp;lt;TYPE&amp;gt;0&amp;lt;/TYPE&amp;gt;&amp;lt;DATATYPE&amp;gt;0&amp;lt;/DATATYPE&amp;gt;&amp;lt;TABLE&amp;gt;view_nbu_slp_image&amp;lt;/TABLE&amp;gt;&amp;lt;REPORTFIELDS&amp;gt;&amp;lt;FIELD cast=&amp;quot;String&amp;quot;condition=&amp;quot;true&amp;quot;display=&amp;quot;true&amp;quot;&amp;gt;&amp;lt;LABEL&amp;gt;Image SLP State&amp;lt;/LABEL&amp;gt;&amp;lt;FIELDNAME&amp;gt;state&amp;lt;/FIELDNAME&amp;gt;&amp;lt;/FIELD&amp;gt;&amp;lt;/REPORTFIELDS&amp;gt;&amp;lt;/XML&amp;gt;";
//        String s = " &lt;XML&gt;                        &lt;TYPE&gt;0&lt;/TYPE&gt;                                    &lt;DATATYPE&gt;0&lt;/DATATYPE&gt;                                    &lt;TABLE&gt;view_nbu_slp_image&lt;/TABLE&gt;            &lt;REPORTFIELDS&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot; key=&quot;true&quot;&gt;                    &lt;LABEL&gt;Master Server&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;master_server&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot; key=&quot;true&quot;&gt;                    &lt;LABEL&gt;Image ID&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;image_id&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;SLP Name&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;slp_name&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;SLP Version&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;slp_version&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;String&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Backup Client Name&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;backup_client_name&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Backup Client Type&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;backup_client_type&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Backup Policy Name&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;backup_policy&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot; starttimefield=&quot;true&quot; endtimefield=&quot;true&quot;&gt;                    &lt;LABEL&gt;Backup Time&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;backup_time&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;Date&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Data Classification&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;data_classification&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Origin Master Server&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;origin_master_server&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot; starttimefield=&quot;true&quot; endtimefield=&quot;true&quot;&gt;                    &lt;LABEL&gt;Image Created Time&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;created_time&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;Date&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot; starttimefield=&quot;true&quot; endtimefield=&quot;true&quot;&gt;                    &lt;LABEL&gt;Image Completed Time&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;completed_time&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;Date&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;Int&quot; condition=&quot;true&quot; display=&quot;true&quot; starttimefield=&quot;true&quot; endtimefield=&quot;true&quot;&gt;                    &lt;LABEL&gt;In Process Time&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;in_process_time&lt;/FIELDNAME&gt;                    &lt;DISPLAYCAST&gt;Date&lt;/DISPLAYCAST&gt;                &lt;/FIELD&gt;                &lt;FIELD cast=&quot;String&quot; condition=&quot;true&quot; display=&quot;true&quot;&gt;                    &lt;LABEL&gt;Image SLP State&lt;/LABEL&gt;                    &lt;FIELDNAME&gt;state&lt;/FIELDNAME&gt;                &lt;/FIELD&gt;            &lt;/REPORTFIELDS&gt;                    &lt;/XML&gt;   ";
//        String s = "  <XML>\n" +
//                "            <CATEGORY>Symantec NetBackup</CATEGORY>\n" +
//                "            <TYPE>0</TYPE>\n" +
//                "            <REPORTCLASS>2</REPORTCLASS>\n" +
//                "            <REPORTTYPE>1</REPORTTYPE>\n" +
//                "            <DATATYPE>0</DATATYPE>\n" +
//                "            <HIDDEN>0</HIDDEN>\n" +
//                "            <SYSTEM>0</SYSTEM>\n" +
//                "            <TABLE>view_nbu_slp_image</TABLE>\n" +
//                "            <REPORTFIELDS>\n" +
//                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\" key=\"true\">\n" +
//                "                    <LABEL>Master Server</LABEL>\n" +
//                "                    <FIELDNAME>master_server</FIELDNAME>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\" key=\"true\">\n" +
//                "                    <LABEL>Image ID</LABEL>\n" +
//                "                    <FIELDNAME>image_id</FIELDNAME>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
//                "                    <LABEL>SLP Name</LABEL>\n" +
//                "                    <FIELDNAME>slp_name</FIELDNAME>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\">\n" +
//                "                    <LABEL>SLP Version</LABEL>\n" +
//                "                    <FIELDNAME>slp_version</FIELDNAME>\n" +
//                "                    <DISPLAYCAST>String</DISPLAYCAST>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
//                "                    <LABEL>Backup Client Name</LABEL>\n" +
//                "                    <FIELDNAME>backup_client_name</FIELDNAME>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
//                "                    <LABEL>Backup Client Type</LABEL>\n" +
//                "                    <FIELDNAME>backup_client_type</FIELDNAME>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
//                "                    <LABEL>Backup Policy Name</LABEL>\n" +
//                "                    <FIELDNAME>backup_policy</FIELDNAME>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\" starttimefield=\"true\" endtimefield=\"true\">\n" +
//                "                    <LABEL>Backup Time</LABEL>\n" +
//                "                    <FIELDNAME>backup_time</FIELDNAME>\n" +
//                "                    <DISPLAYCAST>Date</DISPLAYCAST>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
//                "                    <LABEL>Data Classification</LABEL>\n" +
//                "                    <FIELDNAME>data_classification</FIELDNAME>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
//                "                    <LABEL>Origin Master Server</LABEL>\n" +
//                "                    <FIELDNAME>origin_master_server</FIELDNAME>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\" starttimefield=\"true\" endtimefield=\"true\">\n" +
//                "                    <LABEL>Image Created Time</LABEL>\n" +
//                "                    <FIELDNAME>created_time</FIELDNAME>\n" +
//                "                    <DISPLAYCAST>Date</DISPLAYCAST>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\" starttimefield=\"true\" endtimefield=\"true\">\n" +
//                "                    <LABEL>Image Completed Time</LABEL>\n" +
//                "                    <FIELDNAME>completed_time</FIELDNAME>\n" +
//                "                    <DISPLAYCAST>Date</DISPLAYCAST>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"Int\" condition=\"true\" display=\"true\" starttimefield=\"true\" endtimefield=\"true\">\n" +
//                "                    <LABEL>In Process Time</LABEL>\n" +
//                "                    <FIELDNAME>in_process_time</FIELDNAME>\n" +
//                "                    <DISPLAYCAST>Date</DISPLAYCAST>\n" +
//                "                </FIELD>\n" +
//                "                <FIELD cast=\"String\" condition=\"true\" display=\"true\">\n" +
//                "                    <LABEL>Image SLP State</LABEL>\n" +
//                "                    <FIELDNAME>state</FIELDNAME>\n" +
//                "                </FIELD>\n" +
//                "            </REPORTFIELDS>\n" +
//                "\n" +
//                "            <QUERY>\n" +
//                "                <EXPRESSION cast=\"String\" op=\"is\" type=\"datamine\">\n" +
//                "                    <TABLE nodefield=\"agent_name\" starttimefield=\"@starttimefield@\" endtimefield=\"@endtimefield@\">view_nbu_slp_image</TABLE>\n" +
//                "                    <ROWSET>view_nbu_slp_image</ROWSET>\n" +
//                "                    <SQL>\n" +
//                "                        <SELECT>\n" +
//                "                           SELECT * from view_nbu_slp_image WHERE %%conditions%%;\n" +
//                "                        </SELECT>\n" +
//                "                    </SQL>\n" +
//                "                    <CONDSET>view_nbu_slp_image</CONDSET>\n" +
//                "                </EXPRESSION>\n" +
//                "            </QUERY>\n" +
//                "        </XML>";
        String s = "<controlPanelResult>\n" +
                "   <runIdentifier>6ce87734-ee30-4e82-acc4-3753e4a6fff7</runIdentifier>\n" +
                "   <minSize width=\"200\" height=\"400\"/>\n" +
                "   <background color=\"#ffffff\" imageUrl=\"\" layout=\"centered\"/>\n" +
                "   <capabilities drilldown=\"false\" interaction=\"false\"/>\n" +
                "   <components>\n" +
                "      <report x=\"0\" y=\"0\" width=\"550\" height=\"300\">\n" +
                "         <border>none</border>\n" +
                "         <id>12eb0468-fb78-472a-a63e-8a46db65695f</id>\n" +
                "         <resultUrl>https://localhost:9002/dpa-api/report/result/12eb0468-fb78-472a-a63e-8a46db65695f</resultUrl>\n" +
                "         <reportTemplateId>392d38a9-cd39-4247-916d-5ac31b1dd0af</reportTemplateId>\n" +
                "         <reportTemplateName>Replication Alerts by Group - Bottom 6</reportTemplateName>\n" +
                "         <rendererParameters>&lt;replicationAlertsStatusByGroupBarChartRendererParameters&gt;\n" +
                "  &lt;refresh interval=&quot;0&quot;/&gt;\n" +
                "  &lt;reportHeader show=&quot;true&quot;&gt;\n" +
                "    &lt;title show=&quot;true&quot;&gt;@report-name@&lt;/title&gt;\n" +
                "    &lt;timePeriod show=&quot;true&quot;/&gt;\n" +
                "    &lt;scope show=&quot;true&quot;/&gt;\n" +
                "  &lt;/reportHeader&gt;\n" +
                "  &lt;reportFooter show=&quot;true&quot;&gt;\n" +
                "    &lt;generatedAt show=&quot;true&quot;&gt;Generated At:&lt;/generatedAt&gt;\n" +
                "    &lt;footnote show=&quot;false&quot;/&gt;\n" +
                "  &lt;/reportFooter&gt;\n" +
                "  &lt;plotAreaColor fillType=&quot;Solid&quot; background1=&quot;#ffffff&quot; background2=&quot;#ffffff&quot;/&gt;\n" +
                "  &lt;gridLines showHorizontal=&quot;false&quot; showVertical=&quot;true&quot; color=&quot;#e8e8e8&quot;/&gt;\n" +
                "  &lt;timeAxis labelOrientation=&quot;Horizontal&quot;/&gt;\n" +
                "  &lt;stacked&gt;true&lt;/stacked&gt;\n" +
                "  &lt;orientation&gt;Horizontal&lt;/orientation&gt;\n" +
                "  &lt;views/&gt;\n" +
                "  &lt;series&gt;\n" +
                "    &lt;styles&gt;\n" +
                "      &lt;s t=&quot;Bar&quot; l=&quot;Critical alerts&quot; c=&quot;#cf2323&quot;/&gt;\n" +
                "      &lt;s t=&quot;Bar&quot; l=&quot;Other alerts&quot; c=&quot;#b6b6b6&quot;/&gt;\n" +
                "    &lt;/styles&gt;\n" +
                "  &lt;/series&gt;\n" +
                "  &lt;xAxis type=&quot;Grouped&quot;&gt;\n" +
                "    &lt;groupByfields&gt;\n" +
                "      &lt;field&gt;Group name&lt;/field&gt;\n" +
                "    &lt;/groupByfields&gt;\n" +
                "  &lt;/xAxis&gt;\n" +
                "&lt;/replicationAlertsStatusByGroupBarChartRendererParameters&gt;</rendererParameters>\n" +
                "         <nodeLinks>\n" +
                "            <nodeLink type=\"Group\">\n" +
                "               <id>00000000-0000-0000-0000-000000000001</id>\n" +
                "               <name>Root</name>\n" +
                "               <child type=\"Group\">\n" +
                "                  <id>00000000-0000-0000-0000-000000000010</id>\n" +
                "                  <name>Groups</name>\n" +
                "                  <child type=\"Group\">\n" +
                "                     <id>8ad04346-9abd-4466-8bd8-05306b63519d</id>\n" +
                "                     <name>Configuration</name>\n" +
                "                  </child>\n" +
                "               </child>\n" +
                "            </nodeLink>\n" +
                "         </nodeLinks>\n" +
                "         <window>\n" +
                "            <id>2e40830a-fbd4-429c-a895-1d5b6fc2d961</id>\n" +
                "            <name>Now</name>\n" +
                "         </window>\n" +
                "         <isViewlet>true</isViewlet>\n" +
                "      </report>\n" +
                "      <report x=\"600\" y=\"350\" width=\"550\" height=\"300\">\n" +
                "         <border>none</border>\n" +
                "         <id>eeccaaa8-39e9-4f7e-8416-782b17dc3d28</id>\n" +
                "         <resultUrl>https://localhost:9002/dpa-api/report/result/eeccaaa8-39e9-4f7e-8416-782b17dc3d28</resultUrl>\n" +
                "         <reportTemplateId>b46d669e-9409-4cdf-8155-7432de705061</reportTemplateId>\n" +
                "         <reportTemplateName>Objects with Replication Exposures – Bottom 4</reportTemplateName>\n" +
                "         <rendererParameters>&lt;objectsWithReplicationExposuresBarChartRendererParameters&gt;\n" +
                "  &lt;background&gt;#ffffff&lt;/background&gt;\n" +
                "  &lt;refresh interval=&quot;0&quot;/&gt;\n" +
                "  &lt;reportHeader show=&quot;true&quot;&gt;\n" +
                "    &lt;title font=&quot;Tahoma, Bold, 16&quot; foreground=&quot;#000000&quot; show=&quot;true&quot;&gt;@report-name@&lt;/title&gt;\n" +
                "    &lt;timePeriod font=&quot;Verdana, Plain, 11&quot; foreground=&quot;#000000&quot; show=&quot;true&quot;/&gt;\n" +
                "    &lt;scope font=&quot;Verdana, Plain, 11&quot; foreground=&quot;#000000&quot; show=&quot;true&quot;/&gt;\n" +
                "  &lt;/reportHeader&gt;\n" +
                "  &lt;filterCondition&gt;&amp;lt;EXPRESSION type=&quot;none&quot;&amp;gt;\n" +
                "  &amp;lt;ARG&amp;gt;\n" +
                "    &amp;lt;CONDITION op=&quot;greater than&quot;&amp;gt;\n" +
                "      &amp;lt;FIELD&amp;gt;Number of replication alerts&amp;lt;/FIELD&amp;gt;\n" +
                "      &amp;lt;VALUE type=&quot;static&quot;&amp;gt;0&amp;lt;/VALUE&amp;gt;\n" +
                "    &amp;lt;/CONDITION&amp;gt;\n" +
                "  &amp;lt;/ARG&amp;gt;\n" +
                "&amp;lt;/EXPRESSION&amp;gt;&lt;/filterCondition&gt;\n" +
                "  &lt;reportFooter show=&quot;true&quot;&gt;\n" +
                "    &lt;generatedAt show=&quot;true&quot;&gt;Generated At:&lt;/generatedAt&gt;\n" +
                "    &lt;footnote show=&quot;false&quot;/&gt;\n" +
                "  &lt;/reportFooter&gt;\n" +
                "  &lt;plotAreaColor fillType=&quot;Solid&quot; background1=&quot;#ffffff&quot; background2=&quot;#ffffff&quot;/&gt;\n" +
                "  &lt;gridLines showHorizontal=&quot;false&quot; showVertical=&quot;true&quot; color=&quot;#e8e8e8&quot;/&gt;\n" +
                "  &lt;legend font=&quot;Verdana, Plain, 11&quot; foreground=&quot;#000000&quot; visibility=&quot;Automatic&quot; position=&quot;Automatic&quot;/&gt;\n" +
                "  &lt;axis font=&quot;Verdana, Plain, 11&quot; foreground=&quot;#7b9ebd&quot;/&gt;\n" +
                "  &lt;labels font=&quot;Verdana, Plain, 11&quot; foreground=&quot;#000000&quot;/&gt;\n" +
                "  &lt;timeAxis showLabels=&quot;true&quot; labelOrientation=&quot;Vertical&quot; labelUnit=&quot;Automatic&quot;/&gt;\n" +
                "  &lt;series&gt;\n" +
                "    &lt;visibilities&gt;\n" +
                "      &lt;s l=&quot;Number of replication alerts&quot; v=&quot;true&quot;/&gt;\n" +
                "    &lt;/visibilities&gt;\n" +
                "  &lt;/series&gt;\n" +
                "  &lt;stacked&gt;true&lt;/stacked&gt;\n" +
                "  &lt;orientation&gt;Horizontal&lt;/orientation&gt;\n" +
                "  &lt;views&gt;\n" +
                "    &lt;view type=&quot;Bar&quot; label=&quot;&quot; multiplier=&quot;&quot;&gt;\n" +
                "      &lt;fields/&gt;\n" +
                "    &lt;/view&gt;\n" +
                "  &lt;/views&gt;\n" +
                "  &lt;xAxis type=&quot;Grouped&quot;&gt;\n" +
                "    &lt;groupByfields&gt;\n" +
                "      &lt;field&gt;Node name&lt;/field&gt;\n" +
                "    &lt;/groupByfields&gt;\n" +
                "  &lt;/xAxis&gt;\n" +
                "&lt;/objectsWithReplicationExposuresBarChartRendererParameters&gt;</rendererParameters>\n" +
                "         <nodeLinks>\n" +
                "            <nodeLink type=\"Group\">\n" +
                "               <id>00000000-0000-0000-0000-000000000001</id>\n" +
                "               <name>Root</name>\n" +
                "               <child type=\"Group\">\n" +
                "                  <id>00000000-0000-0000-0000-000000000010</id>\n" +
                "                  <name>Groups</name>\n" +
                "                  <child type=\"Group\">\n" +
                "                     <id>8ad04346-9abd-4466-8bd8-05306b63519d</id>\n" +
                "                     <name>Configuration</name>\n" +
                "                  </child>\n" +
                "               </child>\n" +
                "            </nodeLink>\n" +
                "         </nodeLinks>\n" +
                "         <window>\n" +
                "            <id>2e40830a-fbd4-429c-a895-1d5b6fc2d961</id>\n" +
                "            <name>Now</name>\n" +
                "         </window>\n" +
                "         <isViewlet>true</isViewlet>\n" +
                "      </report>\n" +
                "      <report x=\"0\" y=\"350\" width=\"550\" height=\"300\">\n" +
                "         <border>none</border>\n" +
                "         <id>283bccb5-64ee-4e92-b266-dc3e429c3bbd</id>\n" +
                "         <resultUrl>https://localhost:9002/dpa-api/report/result/283bccb5-64ee-4e92-b266-dc3e429c3bbd</resultUrl>\n" +
                "         <reportTemplateId>39a696a2-02ec-40e6-aa89-1b8bdb052a63</reportTemplateId>\n" +
                "         <reportTemplateName>RecoverPoint Performance - Bottom 4</reportTemplateName>\n" +
                "         <rendererParameters>&lt;RecoverPointPerformanceChartRendererParameters&gt;\n" +
                "  &lt;background&gt;#ffffff&lt;/background&gt;\n" +
                "  &lt;refresh interval=&quot;0&quot;/&gt;\n" +
                "  &lt;reportHeader show=&quot;true&quot;&gt;\n" +
                "    &lt;title font=&quot;Tahoma, Bold, 16&quot; foreground=&quot;#000000&quot; show=&quot;true&quot;&gt;@report-name@&lt;/title&gt;\n" +
                "    &lt;timePeriod font=&quot;Verdana, Plain, 11&quot; foreground=&quot;#000000&quot; show=&quot;true&quot;/&gt;\n" +
                "    &lt;scope font=&quot;Verdana, Plain, 11&quot; foreground=&quot;#000000&quot; show=&quot;true&quot;/&gt;\n" +
                "  &lt;/reportHeader&gt;\n" +
                "  &lt;reportFooter show=&quot;true&quot;&gt;\n" +
                "    &lt;generatedAt show=&quot;true&quot;&gt;Generated At:&lt;/generatedAt&gt;\n" +
                "    &lt;footnote show=&quot;false&quot;/&gt;\n" +
                "  &lt;/reportFooter&gt;\n" +
                "  &lt;plotAreaColor fillType=&quot;Gradient&quot; background1=&quot;#ffffff&quot; background2=&quot;#dfefff&quot;/&gt;\n" +
                "  &lt;gridLines showHorizontal=&quot;true&quot; showVertical=&quot;false&quot; color=&quot;#e8e8e8&quot;/&gt;\n" +
                "  &lt;legend font=&quot;Tahoma, Plain, 11&quot; foreground=&quot;#000000&quot; visibility=&quot;Automatic&quot; position=&quot;Automatic&quot;/&gt;\n" +
                "  &lt;axis font=&quot;Tahoma, Plain, 11&quot; foreground=&quot;#202020&quot;/&gt;\n" +
                "  &lt;labels font=&quot;Tahoma, Plain, 12&quot; foreground=&quot;#000000&quot;/&gt;\n" +
                "  &lt;timeAxis showLabels=&quot;true&quot; labelOrientation=&quot;Vertical&quot; labelUnit=&quot;Automatic&quot;/&gt;\n" +
                "  &lt;stacked&gt;true&lt;/stacked&gt;\n" +
                "  &lt;orientation&gt;Horizontal&lt;/orientation&gt;\n" +
                "  &lt;views&gt;\n" +
                "    &lt;view type=&quot;Bar&quot; label=&quot;Data Lag&quot; unit=&quot;B&quot; multiplier=&quot;M&quot;&gt;\n" +
                "      &lt;fields&gt;\n" +
                "        &lt;field&gt;Average&lt;/field&gt;\n" +
                "        &lt;field&gt;Maximum&lt;/field&gt;\n" +
                "      &lt;/fields&gt;\n" +
                "    &lt;/view&gt;\n" +
                "    &lt;view type=&quot;Bar&quot; label=&quot;Time Lag&quot; unit=&quot;seconds&quot; multiplier=&quot;seconds&quot;&gt;\n" +
                "      &lt;fields&gt;\n" +
                "        &lt;field&gt;Average Time&lt;/field&gt;\n" +
                "        &lt;field&gt;Maximum Time&lt;/field&gt;\n" +
                "      &lt;/fields&gt;\n" +
                "    &lt;/view&gt;\n" +
                "  &lt;/views&gt;\n" +
                "  &lt;series&gt;\n" +
                "    &lt;styles&gt;\n" +
                "      &lt;s t=&quot;Bar&quot; l=&quot;Average&quot; c=&quot;#007dc3&quot;/&gt;\n" +
                "      &lt;s t=&quot;Bar&quot; l=&quot;Maximum&quot; c=&quot;#b42e34&quot;/&gt;\n" +
                "      &lt;s t=&quot;Bar&quot; l=&quot;Average Time&quot; c=&quot;#007dc3&quot;/&gt;\n" +
                "      &lt;s t=&quot;Bar&quot; l=&quot;Maximum Time&quot; c=&quot;#b42e34&quot;/&gt;\n" +
                "    &lt;/styles&gt;\n" +
                "  &lt;/series&gt;\n" +
                "  &lt;xAxis type=&quot;Grouped&quot;&gt;\n" +
                "    &lt;groupByfields&gt;\n" +
                "      &lt;field&gt;Server&lt;/field&gt;\n" +
                "      &lt;field&gt;Consistency Group&lt;/field&gt;\n" +
                "      &lt;field&gt;Name&lt;/field&gt;\n" +
                "    &lt;/groupByfields&gt;\n" +
                "  &lt;/xAxis&gt;\n" +
                "&lt;/RecoverPointPerformanceChartRendererParameters&gt;</rendererParameters>\n" +
                "         <nodeLinks>\n" +
                "            <nodeLink type=\"Group\">\n" +
                "               <id>00000000-0000-0000-0000-000000000001</id>\n" +
                "               <name>Root</name>\n" +
                "               <child type=\"Group\">\n" +
                "                  <id>00000000-0000-0000-0000-000000000010</id>\n" +
                "                  <name>Groups</name>\n" +
                "                  <child type=\"Group\">\n" +
                "                     <id>8ad04346-9abd-4466-8bd8-05306b63519d</id>\n" +
                "                     <name>Configuration</name>\n" +
                "                     <child type=\"Group\">\n" +
                "                        <id>a1d4ab02-13a5-4075-8277-d0eff4f9e787</id>\n" +
                "                        <name>Storage</name>\n" +
                "                     </child>\n" +
                "                  </child>\n" +
                "               </child>\n" +
                "            </nodeLink>\n" +
                "         </nodeLinks>\n" +
                "         <window>\n" +
                "            <id>70497d12-3cf1-4f84-bcc8-6ad717e8caf5</id>\n" +
                "            <name>Last Month</name>\n" +
                "         </window>\n" +
                "         <isViewlet>true</isViewlet>\n" +
                "      </report>\n" +
                "      <report x=\"600\" y=\"0\" width=\"550\" height=\"300\">\n" +
                "         <border>none</border>\n" +
                "         <id>65721647-0b28-496c-acb8-f7ea4adaf415</id>\n" +
                "         <resultUrl>https://localhost:9002/dpa-api/report/result/65721647-0b28-496c-acb8-f7ea4adaf415</resultUrl>\n" +
                "         <reportTemplateId>e8a93229-d056-4eca-b836-d51d236adc1b</reportTemplateId>\n" +
                "         <reportTemplateName>Replication Exposure Age by Group</reportTemplateName>\n" +
                "         <rendererParameters>&lt;replicationExposureByAgeByGroupTableRendererParameters&gt;\n" +
                "  &lt;refresh interval=&quot;0&quot;/&gt;\n" +
                "  &lt;reportHeader show=&quot;true&quot;&gt;\n" +
                "    &lt;title show=&quot;true&quot;&gt;@report-name@&lt;/title&gt;\n" +
                "    &lt;timePeriod show=&quot;true&quot;/&gt;\n" +
                "    &lt;scope show=&quot;true&quot;/&gt;\n" +
                "  &lt;/reportHeader&gt;\n" +
                "  &lt;reportFooter show=&quot;true&quot;&gt;\n" +
                "    &lt;generatedAt show=&quot;true&quot;&gt;Generated At:&lt;/generatedAt&gt;\n" +
                "    &lt;footnote show=&quot;false&quot;/&gt;\n" +
                "  &lt;/reportFooter&gt;\n" +
                "  &lt;tableHeaderStyle font=&quot;Tahoma, Plain, 11&quot; foreground=&quot;#000000&quot; background=&quot;#dae7eb&quot; position=&quot;Top&quot;/&gt;\n" +
                "  &lt;rowStyle font=&quot;Verdana, Plain, 11&quot; foreground=&quot;#000000&quot; background=&quot;#ffffff&quot;/&gt;\n" +
                "  &lt;alternateRowStyle font=&quot;Verdana, Plain, 11&quot; foreground=&quot;#000000&quot; background=&quot;#ffffff&quot;/&gt;\n" +
                "  &lt;gridLines show=&quot;false&quot; color=&quot;#ffffff&quot;/&gt;\n" +
                "  &lt;sortings&gt;\n" +
                "    &lt;sorting field=&quot;1-6 Days&quot; direction=&quot;Descending&quot;/&gt;\n" +
                "    &lt;sorting field=&quot;1-3 Weeks&quot; direction=&quot;Descending&quot;/&gt;\n" +
                "    &lt;sorting field=&quot;Over 3 Weeks&quot; direction=&quot;Descending&quot;/&gt;\n" +
                "  &lt;/sortings&gt;\n" +
                "  &lt;showObjectColumn&gt;Hide&lt;/showObjectColumn&gt;\n" +
                "  &lt;showEmptyColumns&gt;true&lt;/showEmptyColumns&gt;\n" +
                "  &lt;columns&gt;\n" +
                "    &lt;column field=&quot;Group name&quot; visible=&quot;true&quot; cast=&quot;String&quot; alignment=&quot;Automatic&quot; multiplier=&quot;Automatic&quot;/&gt;\n" +
                "    &lt;column field=&quot;1-6 Days&quot; visible=&quot;true&quot; cast=&quot;Long&quot; alignment=&quot;Center&quot; multiplier=&quot;Automatic&quot;/&gt;\n" +
                "    &lt;column field=&quot;1-3 Weeks&quot; visible=&quot;true&quot; cast=&quot;Long&quot; alignment=&quot;Center&quot; multiplier=&quot;Automatic&quot;/&gt;\n" +
                "    &lt;column field=&quot;Over 3 Weeks&quot; visible=&quot;true&quot; cast=&quot;Long&quot; alignment=&quot;Center&quot; multiplier=&quot;Automatic&quot;/&gt;\n" +
                "  &lt;/columns&gt;\n" +
                "  &lt;conditionalRows&gt;\n" +
                "    &lt;conditionalRow borderThickness=&quot;1&quot; borderColor=&quot;#e8e8e8&quot;&gt;\n" +
                "      &lt;expression&gt;&amp;lt;EXPRESSION type=&quot;none&quot;&amp;gt;\n" +
                "  &amp;lt;ARG&amp;gt;\n" +
                "    &amp;lt;CONDITION op=&quot;equal to&quot;&amp;gt;\n" +
                "      &amp;lt;FIELD&amp;gt;1-6 Days&amp;lt;/FIELD&amp;gt;\n" +
                "      &amp;lt;VALUE type=&quot;static&quot;&amp;gt;0&amp;lt;/VALUE&amp;gt;\n" +
                "    &amp;lt;/CONDITION&amp;gt;\n" +
                "  &amp;lt;/ARG&amp;gt;\n" +
                "&amp;lt;/EXPRESSION&amp;gt;&lt;/expression&gt;\n" +
                "      &lt;fields&gt;\n" +
                "        &lt;field name=&quot;1-6 Days&quot;/&gt;\n" +
                "      &lt;/fields&gt;\n" +
                "    &lt;/conditionalRow&gt;\n" +
                "    &lt;conditionalRow borderThickness=&quot;1&quot; borderColor=&quot;#cf2323&quot;&gt;\n" +
                "      &lt;expression&gt;&amp;lt;EXPRESSION type=&quot;none&quot;&amp;gt;\n" +
                "  &amp;lt;ARG&amp;gt;\n" +
                "    &amp;lt;CONDITION op=&quot;greater than&quot;&amp;gt;\n" +
                "      &amp;lt;FIELD&amp;gt;1-6 Days&amp;lt;/FIELD&amp;gt;\n" +
                "      &amp;lt;VALUE type=&quot;static&quot;&amp;gt;0&amp;lt;/VALUE&amp;gt;\n" +
                "    &amp;lt;/CONDITION&amp;gt;\n" +
                "  &amp;lt;/ARG&amp;gt;\n" +
                "&amp;lt;/EXPRESSION&amp;gt;&lt;/expression&gt;\n" +
                "      &lt;fields&gt;\n" +
                "        &lt;field name=&quot;1-6 Days&quot;/&gt;\n" +
                "      &lt;/fields&gt;\n" +
                "    &lt;/conditionalRow&gt;\n" +
                "    &lt;conditionalRow borderThickness=&quot;1&quot; borderColor=&quot;#e8e8e8&quot;&gt;\n" +
                "      &lt;expression&gt;&amp;lt;EXPRESSION type=&quot;none&quot;&amp;gt;\n" +
                "  &amp;lt;ARG&amp;gt;\n" +
                "    &amp;lt;CONDITION op=&quot;equal to&quot;&amp;gt;\n" +
                "      &amp;lt;FIELD&amp;gt;1-3 Weeks&amp;lt;/FIELD&amp;gt;\n" +
                "      &amp;lt;VALUE type=&quot;static&quot;&amp;gt;0&amp;lt;/VALUE&amp;gt;\n" +
                "    &amp;lt;/CONDITION&amp;gt;\n" +
                "  &amp;lt;/ARG&amp;gt;\n" +
                "&amp;lt;/EXPRESSION&amp;gt;&lt;/expression&gt;\n" +
                "      &lt;fields&gt;\n" +
                "        &lt;field name=&quot;1-3 Weeks&quot;/&gt;\n" +
                "      &lt;/fields&gt;\n" +
                "    &lt;/conditionalRow&gt;\n" +
                "    &lt;conditionalRow borderThickness=&quot;2&quot; borderColor=&quot;#cf2323&quot;&gt;\n" +
                "      &lt;expression&gt;&amp;lt;EXPRESSION type=&quot;none&quot;&amp;gt;\n" +
                "  &amp;lt;ARG&amp;gt;\n" +
                "    &amp;lt;CONDITION op=&quot;greater than&quot;&amp;gt;\n" +
                "      &amp;lt;FIELD&amp;gt;1-3 Weeks&amp;lt;/FIELD&amp;gt;\n" +
                "      &amp;lt;VALUE type=&quot;static&quot;&amp;gt;0&amp;lt;/VALUE&amp;gt;\n" +
                "    &amp;lt;/CONDITION&amp;gt;\n" +
                "  &amp;lt;/ARG&amp;gt;\n" +
                "&amp;lt;/EXPRESSION&amp;gt;&lt;/expression&gt;\n" +
                "      &lt;fields&gt;\n" +
                "        &lt;field name=&quot;1-3 Weeks&quot;/&gt;\n" +
                "      &lt;/fields&gt;\n" +
                "    &lt;/conditionalRow&gt;\n" +
                "    &lt;conditionalRow borderThickness=&quot;1&quot; borderColor=&quot;#e8e8e8&quot;&gt;\n" +
                "      &lt;expression&gt;&amp;lt;EXPRESSION type=&quot;none&quot;&amp;gt;\n" +
                "  &amp;lt;ARG&amp;gt;\n" +
                "    &amp;lt;CONDITION op=&quot;equal to&quot;&amp;gt;\n" +
                "      &amp;lt;FIELD&amp;gt;Over 3 Weeks&amp;lt;/FIELD&amp;gt;\n" +
                "      &amp;lt;VALUE type=&quot;static&quot;&amp;gt;0&amp;lt;/VALUE&amp;gt;\n" +
                "    &amp;lt;/CONDITION&amp;gt;\n" +
                "  &amp;lt;/ARG&amp;gt;\n" +
                "&amp;lt;/EXPRESSION&amp;gt;&lt;/expression&gt;\n" +
                "      &lt;fields&gt;\n" +
                "        &lt;field name=&quot;Over 3 Weeks&quot;/&gt;\n" +
                "      &lt;/fields&gt;\n" +
                "    &lt;/conditionalRow&gt;\n" +
                "    &lt;conditionalRow borderThickness=&quot;3&quot; borderColor=&quot;#cf2323&quot;&gt;\n" +
                "      &lt;expression&gt;&amp;lt;EXPRESSION type=&quot;none&quot;&amp;gt;\n" +
                "  &amp;lt;ARG&amp;gt;\n" +
                "    &amp;lt;CONDITION op=&quot;greater than&quot;&amp;gt;\n" +
                "      &amp;lt;FIELD&amp;gt;Over 3 Weeks&amp;lt;/FIELD&amp;gt;\n" +
                "      &amp;lt;VALUE type=&quot;static&quot;&amp;gt;0&amp;lt;/VALUE&amp;gt;\n" +
                "    &amp;lt;/CONDITION&amp;gt;\n" +
                "  &amp;lt;/ARG&amp;gt;\n" +
                "&amp;lt;/EXPRESSION&amp;gt;&lt;/expression&gt;\n" +
                "      &lt;fields&gt;\n" +
                "        &lt;field name=&quot;Over 3 Weeks&quot;/&gt;\n" +
                "      &lt;/fields&gt;\n" +
                "    &lt;/conditionalRow&gt;\n" +
                "  &lt;/conditionalRows&gt;\n" +
                "  &lt;summaryRows/&gt;\n" +
                "&lt;/replicationExposureByAgeByGroupTableRendererParameters&gt;</rendererParameters>\n" +
                "         <nodeLinks>\n" +
                "            <nodeLink type=\"Group\">\n" +
                "               <id>00000000-0000-0000-0000-000000000001</id>\n" +
                "               <name>Root</name>\n" +
                "               <child type=\"Group\">\n" +
                "                  <id>00000000-0000-0000-0000-000000000010</id>\n" +
                "                  <name>Groups</name>\n" +
                "                  <child type=\"Group\">\n" +
                "                     <id>8ad04346-9abd-4466-8bd8-05306b63519d</id>\n" +
                "                     <name>Configuration</name>\n" +
                "                  </child>\n" +
                "               </child>\n" +
                "            </nodeLink>\n" +
                "         </nodeLinks>\n" +
                "         <window>\n" +
                "            <id>70497d12-3cf1-4f84-bcc8-6ad717e8caf5</id>\n" +
                "            <name>Last Month</name>\n" +
                "         </window>\n" +
                "         <isViewlet>true</isViewlet>\n" +
                "      </report>\n" +
                "   </components>\n" +
                "   <params>\n" +
                "      <nodeLinks>\n" +
                "         <nodeLink type=\"Group\">\n" +
                "            <id>00000000-0000-0000-0000-000000000001</id>\n" +
                "            <name>Root</name>\n" +
                "            <child type=\"Group\">\n" +
                "               <id>00000000-0000-0000-0000-000000000010</id>\n" +
                "               <name>Groups</name>\n" +
                "               <child type=\"Group\">\n" +
                "                  <id>8ad04346-9abd-4466-8bd8-05306b63519d</id>\n" +
                "                  <name>Configuration</name>\n" +
                "               </child>\n" +
                "            </child>\n" +
                "         </nodeLink>\n" +
                "      </nodeLinks>\n" +
                "      <timeConstraints class=\"com.emc.dpa.restapi.runreport.WindowTimeConstraintsXML\" type=\"window\">\n" +
                "         <window version=\"1\">\n" +
                "            <id>6eeb93f7-4c65-4e18-bead-43f47de10ed1</id>\n" +
                "            <name>Last Day</name>\n" +
                "         </window>\n" +
                "      </timeConstraints>\n" +
                "      <runInDebug>false</runInDebug>\n" +
                "   </params>\n" +
                "</controlPanelResult>";

        Clipboard.clip(decodeXML(s));
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
