# Internationalization with i18n

Angular cli supports the i18n standard out of the box, so what follows is a short introduction/tutorial on how to add translations to your app compile them aot and dynamically change them at runtime

The resources where i got most of the informations from were:
- https://angular.io/guide/i18n
- https://angular-templates.io/tutorials/about/angular-internationalization-i18n-multi-language-app

A website where you can translate your xliff file
- http://www.beyondf.com/tranzapp/translate/

## Define which elements should be translated
You do this by adding an extra attribute `i18n` to the HTML element which loos like this:

```html
      <h1 i18n="No room header|The header text that is displayed when no room is selected@@dashboard/room-measurements/noRoomHeader" class="title">
        Please select a room!
      </h1>
```
This example is from the `RoomMeasurementsComponent`s markup. The syntax of the i18n tag is defined like:

```html
      <h1 i18n="Meaning of the phrase|More detailed description of the phrase@@id">Some phrase</h1>
```
the id doesnt have to be a file path but it makes sense to do it especially if the developer is also the translator (normally you would send the files `xliff` file to a translator that will edit it with an xliff editor and send you the translated file back)

## Generate the translation source file (xlf, xliff)

> ng xi18n

Will output a translation source file. If you inspect it you can see all you defined tags in a xml structure where al the `<source>` tags have to be translated and added as `<target>` below

```xml
<trans-unit id="toolbar-title" datatype="html">
  <source>My Application</source>
  <target>Mi Aplicación</target>
  <context-group purpose="location">
    <context context-type="sourcefile">app/app.component.html</context>
    <context context-type="linenumber">6</context>
  </context-group>
</trans-unit>
```

# Use the files to serve your app
This will serve the app with a german locale and the base href `/de`
> ng serve --configuration=de

You have to do a lot of configurations for this but since this step is already done i wont explain it here. If you are curious or have to redo the configs either search for recent tutorials or search the angular docs. You can also look at the links aat the top if they are not outdated

## Configure nginx to serve the right files
this answer basically explains it: https://stackoverflow.com/a/49990548
