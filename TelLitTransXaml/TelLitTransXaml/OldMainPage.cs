using System;
using System.Collections.Generic;
using System.Text;
using Xamarin.Essentials;
using Xamarin.Forms;

namespace TelLitTransXaml
{
    public class OldMainPage : ContentPage
    {
        Entry phoneNumberText;
        Button translateButton;
        Button callButton;
        string translatedNumber;

        public OldMainPage()
        {
            this.Padding = new Thickness(20, 44, 20, 20);

            StackLayout panel = new StackLayout
            {
                Spacing = 15
            };

            panel.Children.Add(new Label
            {
                Text = "Introduceti textul:",
                FontSize = Device.GetNamedSize(NamedSize.Large, typeof(Label))
            });

            panel.Children.Add(phoneNumberText = new Entry
            {
                Text = "07-NEWPARTS",
            });

            panel.Children.Add(translateButton = new Button
            {
                Text = "Traduceti"
            });

            panel.Children.Add(callButton = new Button
            {
                Text = "Suna",
                IsEnabled = false,
            });

            translateButton.Clicked += OnTranslate;
            callButton.Clicked += OnCall;
            this.Content = panel;
        }

        void OnTranslate(object sender, EventArgs e)
        {
            string enteredNumber = phoneNumberText.Text;
            translatedNumber = TelLitTransXaml.TelTranslator.ToNumber(enteredNumber);

            if (!string.IsNullOrEmpty(translatedNumber))
            {
                callButton.IsEnabled = true;
                callButton.Text = "Suna " + translatedNumber;
            }
            else
            {
                callButton.IsEnabled = false;
                callButton.Text = "Suna";
            }
        }

        async void OnCall(object sender, System.EventArgs e)
        {
            if (await this.DisplayAlert(
                "Formati numarul",
                "Doriti sa sunati " + translatedNumber + "?",
                "Da",
                "Nu"))
            {
                try
                {
                    PhoneDialer.Open(translatedNumber);
                }
                catch (ArgumentNullException)
                {
                    await DisplayAlert("Nu se poate forma numarul", "Numar invalid", "OK");
                }
                catch (FeatureNotSupportedException)
                {
                    await DisplayAlert("Nu se poate forma numarul", "Numar gresit", "OK");
                }
                catch (Exception)
                {
                    // Other error has occurred.
                    await DisplayAlert("Nu se poate forma numarul", "Eroare de apelare", "OK");
                }
            }
        }
    }
}

