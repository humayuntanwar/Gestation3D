package com.example.humayunt.templateui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TermsCondition extends AppCompatActivity {
    TextView terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_condition);
        terms = (TextView) findViewById(R.id.terms);
        terms.setText("Terms and Conditions (\"Terms\")\n" +
                "\n" +
                "Last updated: 2/20/2018)\n" +
                "\n" +
                "Please read these Terms and Conditions (\"Terms\", \"Terms and Conditions\") carefully before using the http://www.gestation3d.com  website and theGestation 3d Mobile App \n" +
                "\n" +
                "Your access to and use of the Service is conditioned on your acceptance of and compliance with these Terms. These Terms apply to all visitors, users and others who access or use the Service.\n" +
                "\n" +
                "By accessing or using the Service you agree to be bound by these Terms. If you disagree with any part of the terms then you may not access the Service.\n" +
                "\n" +
                "Purchases\n" +
                "\n" +
                "If you wish to purchase any product or service made available through the Service (\"Purchase\"), you may be asked to supply certain information relevant to your Purchase including, without limitation, your …\n" +
                "\n" +
                "The Purchases section is for businesses that sell online (physical or digital). For the full disclosure section, create your own Terms and Conditions.\n" +
                "\n" +
                "Subscriptions\n" +
                "\n" +
                "Some parts of the Service are billed on a subscription basis (\"Subscription(s)\"). You will be billed in advance on a recurring ...\n" +
                "\n" +
                "The Subscriptions section is for SaaS businesses. For the full disclosure section, create your own Terms and Conditions.\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Content\n" +
                "\n" +
                "Our Service allows you to post, link, store, share and otherwise make available certain information, text, graphics, videos, or other material (\"Content\"). You are responsible for the …\n" +
                "\n" +
                "The Content section is for businesses that allow users to create, edit, share, make content on their websites or apps. For the full disclosure section, create your own Terms and Conditions.\n" +
                "\n" +
                "Links To Other Web Sites\n" +
                "\n" +
                "Our Service may contain links to third-party web sites or services that are not owned or controlled byGestation 3D.\n" +
                "\n" +
                " Gestation 3D has no control over, and assumes no responsibility for, the content, privacy policies, or practices of any third party web sites or services. You further acknowledge and agree that Gestation 3D shall not be responsible or liable, directly or indirectly, for any damage or loss caused or alleged to be caused by or in connection with use of or reliance on any such content, goods or services available on or through any such web sites or services.\n" +
                "\n" +
                "Changes\n" +
                "\n" +
                "We reserve the right, at our sole discretion, to modify or replace these Terms at any time. If a revision is material we will try to provide at least 30 (change this) days' notice prior to any new terms taking effect. What constitutes a material change will be determined at our sole discretion.\n" +
                "\n" +
                "Contact Us\n" +
                "\n" +
                "If you have any questions about these Terms, please contact us.\n");
    }
}
