using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace TelLitTransXaml
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MainPage : ContentPage
    {
        string translatedNumber;
        public MainPage()
        {
            InitializeComponent();
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