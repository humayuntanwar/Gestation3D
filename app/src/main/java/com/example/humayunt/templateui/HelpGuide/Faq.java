package com.example.humayunt.templateui.HelpGuide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.humayunt.templateui.R;

public class Faq extends AppCompatActivity {
    TextView faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        faq = (TextView) findViewById(R.id.faqs);
        faq.setText("Is the Mobile App secure?\n" +
                "\n" +
                "Yes! All critical information is encrypted and no personal information is stored on your mobile device. However, mobile devices do offer you the ability to store your login information for apps installed on the device. If you choose to store your login information, any person who has access to your mobile device can access your account.\n" +
                "\n" +

                "\n" +
                "What features does the Mobile App have?\n" +
                "\n" +
                "Both the Mobile Apps and the Mobile Web App give you the ability to you to access your account information, view news releases, report an outage, and contact us via email or phone.\n" +
                "\n" +
                "Once you've installed a Mobile App on your phone, you'll also have the ability to view a map of our offices and payment locations.\n" +
                "\n" +

                "\n" +
                "How do I get the Mobile App for my phone?\n" +
                "\n" +
                "Simply look for our name in the App Store or in the Android Market. In the Android Market, if you can't find our App, that likely means your phone is not supported - see the list of supported operating systems.\n" +
                "\n" +

                "\n" +
                "Do I have to buy the Mobile App?\n" +
                "\n" +
                "No. Our Mobile App is completely free to download and install.\n" +
                "\n" +

                "\n" +
                "I have multiple accounts. Can I see them all in the Mobile App and the Mobile Website?\n" +
                "\n" +
                "Yes. Once you've logged in, you'll be asked to select the account you wish to view. If you only have one account, the details for that account will show up as soon as you log in.\n" +
                "\n" +

                "\n" +
                "How current is the account information I see in the Mobile App?\n" +
                "\n" +
                "The information you see in the Mobile App and in the Mobile Website is shown in real-time, so it's always accurate. However, if you keep the Mobile App or Mobile Website open for an extended period of time, you should refresh the page by selecting a new option in order to ensure the information is still current.\n" +
                "\n" +

                "\n" +
                "How do I find your offices and payment locations? Do I have to log in first?\n" +
                "\n" +
                "You do not have to log in to view addresses or maps to our office locations or even to get our contact information. Simply open the App and use the \"Offices\" link on the main screen.");
    }
}
